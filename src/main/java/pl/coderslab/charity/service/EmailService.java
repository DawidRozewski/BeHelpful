package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.AppUser;

public interface EmailService {
    void sendVerificationEmail(AppUser appUser);
}

