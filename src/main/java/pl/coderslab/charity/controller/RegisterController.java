package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private final UserRepository userRepository;

    @GetMapping()
    public String prepForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String save(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if(result.hasErrors()) {
            return "register";
        }
        userRepository.save(user);

        return "redirect:";

    }



}
