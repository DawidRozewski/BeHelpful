package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.DonationRepository;

import java.util.List;


@Service
@AllArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;

    @Override
    public long getAllBags() {
        List<Donation> donations = donationRepository.findAll();

        return donations.stream()
                .map(Donation::getQuantity)
                .count();
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
}
