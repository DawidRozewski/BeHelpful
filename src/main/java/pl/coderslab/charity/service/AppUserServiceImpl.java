package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.repository.AppUserRepository;

import java.util.UUID;

@AllArgsConstructor
@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Override
    @Async
    public void save(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        String token = UUID.randomUUID().toString();
        appUser.setVerificationToken(token);
        appUser.setEnabled(false);

        appUserRepository.save(appUser);
        emailService.sendVerificationEmail(appUser);

    }

    @Override
    public String getViewAfterVerification(String token) {
        if (verify(token)) {
            return "verify-success";
        } else {
            return "verify-fail";
        }
    }

    @Override
    public boolean verify(String verificationToken) {
        AppUser appUser = appUserRepository.findByVerificationToken(verificationToken);
        if (appUser == null || appUser.isEnabled()) {
            return false;
        } else {
            appUser.setVerificationToken(null);
            appUser.setEnabled(true);
            appUserRepository.save(appUser);
            return true;
        }
    }

    @Override
    public String comparePasswords(AppUser appUser, BindingResult result) {
        if(!checkPassword(appUser.getPassword(), appUser.getRepassword())) {
            result.rejectValue("repassword", "error.user", "Podane hasła nie są zgodne.");
            return "/register";
        }
        return null;
    }


    @Override
    public AppUser findByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    private boolean checkPassword(String password, String repassword) {
        return password.equals(repassword);
    }
}
