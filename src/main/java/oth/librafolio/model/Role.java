package oth.librafolio.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Ontheheavens
 * @since 02.05.2024
 */
public enum Role implements GrantedAuthority {
    USER("USER"),
    ADMIN("ADMIN");

    private final String name;

    Role(String inputName) {
        this.name = inputName;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
