package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.Donation;

import java.security.Principal;
import java.util.List;

public interface DonationService {

    long getAllBags();
    long getDonationsQuantity();
    List<Donation> findAll();
    void save(Donation donation, Principal principal);
    List<Donation> findAllByAppUserId(Principal principal);
}
