package pl.coderslab.charity.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.service.DonationService;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class DashboardController {

    private final DonationService donationService;

    @GetMapping("/dashboard")
    public String viewDonations(Model model, Principal principal) {
        model.addAttribute("donations",donationService.findAllByAppUserId(principal));

        return "dashboard";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}



