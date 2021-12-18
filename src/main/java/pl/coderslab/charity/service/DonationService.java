package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.Donation;

import java.util.List;

public interface DonationService {

    long getAllBags();
    long getDonationsQuantity();
    List<Donation> findAll();
    void save(Donation donation);
}
