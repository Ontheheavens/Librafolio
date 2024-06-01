package oth.librafolio.model.documents;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import oth.librafolio.dto.documents.BookDTO;

import java.util.List;

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
    public Book(String title, String description, String thumbnail,
                String link, String language, String author, String genre,
                int publicationYear, List<String> tags) {
        super(title, description, thumbnail, link, language, tags);
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }

    public Book() {
    }

    public static Book fromDTO(BookDTO bookDTO) {
        Book.BookBuilder book = Book.builder();

        book.title(bookDTO.title())
                .description(bookDTO.description())
                .link(bookDTO.link())
                .language(bookDTO.language())
                .author(bookDTO.author())
                .genre(bookDTO.genre())
                .publicationYear(bookDTO.publicationYear())
                .thumbnail(bookDTO.thumbnail());

        if (bookDTO.tags() != null) {
            book.tags(bookDTO.tags());
        }

        return book.build();
    }

}
