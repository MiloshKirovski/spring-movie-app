package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.User;
import mk.finki.ukim.wp.lab.model.enumeration.Role;

import java.util.Optional;

public interface UserService {
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role);
    public Optional<User> findByUsername(String username);
}
