package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.Repository.inmemory.UserRepository;
import mk.finki.ukim.wp.lab.Repository.jpa.UserRepositoryJpa;
import mk.finki.ukim.wp.lab.model.User;
import mk.finki.ukim.wp.lab.model.enumeration.Role;
import mk.finki.ukim.wp.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.wp.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.wp.lab.model.exceptions.UsernameAlreadyExistsException;
import mk.finki.ukim.wp.lab.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepositoryJpa userRepository;

    public UserServiceImpl(UserRepositoryJpa userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || repeatPassword.isEmpty())
            throw new InvalidArgumentsException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);

        User user = new User(username, password, name, surname, role);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
