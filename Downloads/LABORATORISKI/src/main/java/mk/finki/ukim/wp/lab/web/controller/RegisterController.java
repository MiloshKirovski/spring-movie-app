package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.enumeration.Role;
import mk.finki.ukim.wp.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.wp.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.wp.lab.service.AuthService;
import mk.finki.ukim.wp.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;

    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam(required = false) Role role) {

        try {
            System.out.println(username+ " " + password+ " " + repeatedPassword+ " " + name + " " + surname + " " + role);
            this.authService.register(username, password, repeatedPassword, name, surname, Role.ROLE_USER);
            return "redirect:/login";
        } catch (PasswordsDoNotMatchException | InvalidArgumentsException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
