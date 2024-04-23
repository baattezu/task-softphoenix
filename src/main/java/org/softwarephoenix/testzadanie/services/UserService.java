package org.softwarephoenix.testzadanie.services;

import org.softwarephoenix.testzadanie.entities.User;

public interface UserService {
    void save(User user);
    boolean existsByUsername(String username);

}
