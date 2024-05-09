package oth.librafolio.dto;

import jakarta.validation.constraints.NotEmpty;

/**
 * @author Ontheheavens
 * @since 03.05.2024
 */
public record LoginDTO(
        @NotEmpty
        String username,

        @NotEmpty
        String password) {

}
