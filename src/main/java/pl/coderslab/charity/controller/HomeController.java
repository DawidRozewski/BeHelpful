package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.AppUserService;
import pl.coderslab.charity.service.DonationService;

@Controller
@AllArgsConstructor
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationService donationService;
    private final AppUserService appUserService;

    @GetMapping("/")
    public String homeAction(Model model){
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("bagsQuantity", donationService.getAllBags());
        model.addAttribute("donationsQuantity", donationService.getDonationsQuantity());
        return "index";
    }



}
