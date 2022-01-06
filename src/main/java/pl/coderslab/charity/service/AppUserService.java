package pl.coderslab.charity.service;

import org.springframework.validation.BindingResult;
import pl.coderslab.charity.entity.AppUser;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface AppUserService {

    void save(AppUser appUser) throws MessagingException, UnsupportedEncodingException;
    boolean verify(String verificationToken);
    String getViewAfterVerification(String code);
    String comparePasswords(AppUser appUser, BindingResult result);
    AppUser findByEmail(String email);

}
