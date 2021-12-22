package pl.coderslab.charity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.repository.AppUserRepository;

import java.util.Collections;

public class CurrentUser implements UserDetailsService {

    private AppUserRepository appUserRepository;

    @Autowired
    private void setUserRepository(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return getUserDetails(email);
    }

    private UserDetails getUserDetails(String email) {
        AppUser appUser = appUserRepository.findByEmail(email);
        if(appUser != null && appUser.isEnabled()) {
            GrantedAuthority currentUser = new SimpleGrantedAuthority(appUser.getROLE());
            return new org.springframework.security.core.userdetails.User(
                    appUser.getEmail(),
                    appUser.getPassword(),
                    Collections.singleton(currentUser));
        }

        throw new UsernameNotFoundException(email);
    }




}
