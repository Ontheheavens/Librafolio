package oth.librafolio.service.documents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oth.librafolio.dto.documents.BookDTO;
import oth.librafolio.model.documents.Book;
import oth.librafolio.repository.documents.BookRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ontheheavens
 * @since 01.06.2024
 */
@Service
public class BookService {

    public static final String BOOK_NOT_FOUND = "Book not found";
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public Book findById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.orElse(null);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book createBook(BookDTO bookDTO) {
        Book createdBook = Book.fromDTO(bookDTO);
        return bookRepository.save(createdBook);
    }

    public Book updateBook(Long id, BookDTO bookDto) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        Book existingBook = bookOptional.orElseThrow(
                () -> new IllegalArgumentException(BOOK_NOT_FOUND)
        );

        existingBook.setTitle(bookDto.title());
        existingBook.setDescription(bookDto.description());
        existingBook.setThumbnail(bookDto.thumbnail());
        existingBook.setLink(bookDto.link());
        existingBook.setLanguage(bookDto.language());
        existingBook.setAuthor(bookDto.author());
        existingBook.setGenre(bookDto.genre());
        existingBook.setPublicationYear(bookDto.publicationYear());
        existingBook.setTags(bookDto.tags());

        return bookRepository.save(existingBook);
    }


    public boolean deleteBook(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isEmpty()) {
            return false;
        }
        bookRepository.deleteById(id);

        return true;
    }

}