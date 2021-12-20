package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.repository.AppUserRepository;
import pl.coderslab.charity.service.AppUserServiceImpl;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private final AppUserServiceImpl appUserService;

    @GetMapping()
    public String prepForm(Model model){
        model.addAttribute("appUser", new AppUser());
        return "register";
    }

    @PostMapping
    public String save(@ModelAttribute("appUser") @Valid AppUser appUser, BindingResult result) {
        if(!checkPassword(appUser.getPassword(), appUser.getRepassword())) {
            result.rejectValue("repassword", "error.user", "Podane hasła nie są zgodne.");
            return "/register";
        }
        if(result.hasErrors()) {
            return "register";
        }
        appUserService.save(appUser);
        return "redirect:/login";
    }

    private boolean checkPassword(String password, String repassword) {
        return password.equals(repassword);
    }



}
