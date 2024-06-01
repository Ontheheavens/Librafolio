package oth.librafolio.model.documents;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ontheheavens
 * @since 06.05.2024
 */
@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
public class Document {

    public static final String DOCUMENT_ID_FIELD = "document_id";

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DOCUMENT_ID_FIELD)
    private Long id;

    @Setter
    private String title;

    @Setter
    private String description;

    @Setter
    private String thumbnail;

    @Setter
    private String link;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

    @Setter
    private String language;

    public Document(String title, String description, String thumbnail, String link) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.link = link;
    }

    public Document() {

    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

}
