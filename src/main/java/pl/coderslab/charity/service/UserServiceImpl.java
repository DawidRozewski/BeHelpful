package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
