package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.Repository.jpa.UserRepositoryJpa;
import mk.finki.ukim.wp.lab.model.User;
import mk.finki.ukim.wp.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.wp.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.wp.lab.service.AuthService;
import org.springframework.stereotype.Service;
import mk.finki.ukim.wp.lab.model.exceptions.UsernameAlreadyExistsException;


@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepositoryJpa userRepository;

    // Se povikuva pri samoto instanciranje, a userRepository se injektira VNATRE od Spring Boot
    public AuthServiceImpl(UserRepositoryJpa userRepository) {

        this.userRepository = userRepository;
    }
    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return null;
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(RuntimeException::new);  // mora exception bidekji moze da e Optional NULL
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || repeatPassword.isEmpty())
            throw new InvalidArgumentsException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent() ||
                !this.userRepository.findByUsername(username).isEmpty())
            throw new UsernameAlreadyExistsException(username);

        User user = new User(username, password, name, surname);
        return userRepository.save(user);
    }
}
