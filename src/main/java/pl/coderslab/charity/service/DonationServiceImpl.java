package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.AppUserRepository;
import pl.coderslab.charity.repository.DonationRepository;

import java.security.Principal;
import java.util.List;


@Service
@AllArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;
    private final AppUserRepository appUserRepository;

    @Override
    public long getAllBags() {
        List<Donation> donations = donationRepository.findAll();

        return donations.stream()
                .map(Donation::getQuantity)
                .reduce(0, Integer::sum);
    }

    @Override
    public long getDonationsQuantity() {
        List<Donation> donations = donationRepository.findAll();
        return donations.size();
    }

    @Override
    public List<Donation> findAll() {
        return donationRepository.findAll();
    }

    @Override
    public void save(Donation donation, Principal principal) {
        AppUser appUser = getAppUser(principal);
        donation.setAppUser(appUser);
        donationRepository.save(donation);
    }

    @Override
    public List<Donation> findAllByAppUserId(Principal principal) {
        AppUser appUser = getAppUser(principal);
        return donationRepository.findAllByAppUserId(appUser.getId());
    }

    private AppUser getAppUser(Principal principal) {
        String email = principal.getName();
        return appUserRepository.findByEmail(email);
    }
}
