package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.service.AppUserService;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@Controller
@AllArgsConstructor
public class RegisterController {

    private final AppUserService appUserService;

    @GetMapping("/register")
    public String prepForm(Model model) {
        model.addAttribute("appUser", new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String save(@ModelAttribute("appUser") @Valid AppUser appUser, BindingResult result) throws MessagingException, UnsupportedEncodingException {
        appUserService.checkPasswords(appUser, result);
        if (result.hasErrors()) {
            return "register";
        }
        appUserService.save(appUser);

        return "register-confirmation";
    }


    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        return appUserService.getViewAfterVerification(code);
    }



}
