package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.AppUser;

public interface AppUserService {

    AppUser getByEmail(String email);
    void save(AppUser appUser);

}
