package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.finki.ukim.wp.lab.model.enumeration.Role;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private String username;

    private String password;

    private String name;

    private String surname;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User(String username, String password, String name, String surname, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }
    public User(){}
}
