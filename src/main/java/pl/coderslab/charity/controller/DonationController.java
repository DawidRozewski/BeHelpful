package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.EmailService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/form")
@AllArgsConstructor
public class DonationController {

    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    private final DonationService donationService;
    private final EmailService emailService;

    @GetMapping
     public String prepForm(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("donation", new Donation());
         return "form";

    }

    @PostMapping
     public String form(@ModelAttribute("donation") @Valid Donation donation, BindingResult result, Principal principal) {
        if(result.hasErrors()){
            return "form";
        }
        donationService.save(donation);
        emailService.sendDonationEmail(donation, principal);
         return "form-confirmation";

    }




}
