package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.DonationServiceImpl;

@Controller
@AllArgsConstructor
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationServiceImpl donationService;

    @GetMapping("/")
    public String homeAction(Model model){
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("bagsQuantity", donationService.getAllBags());
        model.addAttribute("donationsQuantity", donationService.getDonationsQuantity());
        return "index";
    }



}
