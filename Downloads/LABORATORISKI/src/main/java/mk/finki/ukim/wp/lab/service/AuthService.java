package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.User;
import mk.finki.ukim.wp.lab.model.enumeration.Role;

public interface AuthService {
    User login(String username, String password);
    User register(String username, String password, String repeatRassword, String name, String surname, Role role);
}
