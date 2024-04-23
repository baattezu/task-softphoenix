package org.softwarephoenix.testzadanie.services.implementations;

import lombok.AllArgsConstructor;
import org.softwarephoenix.testzadanie.entities.User;
import org.softwarephoenix.testzadanie.repos.UserRepository;
import org.softwarephoenix.testzadanie.services.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
