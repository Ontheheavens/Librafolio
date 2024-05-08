package oth.librafolio.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

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

    private String email;

    @Column(columnDefinition = "boolean default true", nullable = false)
    private boolean enabled;
    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean tokenExpired;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isTokenExpired() {
        return tokenExpired;
    }

    public void setTokenExpired(boolean tokenExpired) {
        this.tokenExpired = tokenExpired;
    }

}