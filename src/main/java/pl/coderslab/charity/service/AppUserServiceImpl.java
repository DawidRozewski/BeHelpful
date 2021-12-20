package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.repository.AppUserRepository;

@AllArgsConstructor
@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser getByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    @Override
    public void save(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);

    }



}
