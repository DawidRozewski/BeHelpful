package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.entity.Donation;

import java.security.Principal;

public interface EmailService {
    void sendVerificationEmail(AppUser appUser);
    void sendDonationEmail(Donation donation, Principal principal);

}

