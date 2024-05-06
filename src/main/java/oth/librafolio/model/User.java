package oth.librafolio.model;

import jakarta.persistence.*;

/**
 * @author Ontheheavens
 * @since 02.05.2024
 */
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {

    }

    public User(String inputUsername, String inputPassword, Role inputRole) {
        this.username = inputUsername;
        this.password = inputPassword;
        this.role = inputRole;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String inputUsername) {
        this.username = inputUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String inputPassword) {
        this.password = inputPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role inputRole) {
        this.role = inputRole;
    }

}