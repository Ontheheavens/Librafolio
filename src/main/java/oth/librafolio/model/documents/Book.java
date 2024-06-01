package oth.librafolio.model.documents;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Ontheheavens
 * @since 01.06.2024
 */
@Entity
@PrimaryKeyJoinColumn(name = Document.DOCUMENT_ID_FIELD)
@Getter
@Setter
public class Book extends Document {

    private String author;
    private String genre;
    private int publicationYear;

    @Builder
    public Book(String title, String description, String thumbnail, String link, String author, String genre, int publicationYear) {
        super(title, description, thumbnail, link);
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }

    public Book() {
    }

}
