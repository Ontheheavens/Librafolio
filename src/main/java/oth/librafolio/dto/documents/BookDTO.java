package oth.librafolio.dto.documents;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * @author Ontheheavens
 * @since 01.06.2024
 */
public record BookDTO(
        @NotBlank
        String title,
        String description,
        String thumbnail,
        String link,
        @NotBlank
        String language,
        @NotBlank
        String author,
        @NotBlank
        String genre,
        @NotNull
        int publicationYear,
        List<String> tags) {

}
