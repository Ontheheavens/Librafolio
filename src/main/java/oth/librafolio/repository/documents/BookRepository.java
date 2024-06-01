package oth.librafolio.repository.documents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import oth.librafolio.model.documents.Book;

import java.util.List;

/**
 * @author Ontheheavens
 * @since 01.06.2024
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(String author);

}
