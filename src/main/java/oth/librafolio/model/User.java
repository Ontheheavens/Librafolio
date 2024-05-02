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

}