package oth.librafolio.controller.documents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oth.librafolio.dto.documents.BookDTO;
import oth.librafolio.model.documents.Book;
import oth.librafolio.service.documents.BookService;

import java.util.List;

/**
 * @author Ontheheavens
 * @since 01.06.2024
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam("author") String author) {
        List<Book> books = bookService.findByAuthor(author);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.findById(id);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDto) {
        Book updatedBook = bookService.updateBook(id, bookDto);
        String bookName = "Book " + updatedBook.getTitle() + " updated successfully";
        return new ResponseEntity<>(bookName, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        boolean result = bookService.deleteBook(id);
        if (!result) {
            return new ResponseEntity<>("Book not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Book deleted successfully");
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBook(@RequestBody BookDTO bookDto) {
        Book createdBook = bookService.createBook(bookDto);
        String bookName = "Book " + createdBook.getTitle() + " created successfully.";
        return new ResponseEntity<>(bookName, HttpStatus.CREATED);
    }

}
