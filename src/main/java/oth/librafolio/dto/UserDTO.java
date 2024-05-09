package oth.librafolio.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @author Ontheheavens
 * @since 03.05.2024
 */
public record UserDTO(
        @NotEmpty
        String username,

        @NotEmpty
        String password) {

}
