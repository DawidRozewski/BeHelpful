package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.DonationServiceImpl;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/form")
@AllArgsConstructor
public class DonationController {

    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    private final DonationServiceImpl donationService;

    @GetMapping
     public String prepForm(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("donation", new Donation());
         return "form";

    }

    @PostMapping
     public String form(@ModelAttribute("donation") Donation donation) {
        donationService.save(donation);
         return "form-confirmation";

    }




}
