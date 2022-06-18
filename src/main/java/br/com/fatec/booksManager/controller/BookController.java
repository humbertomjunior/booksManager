package br.com.fatec.booksManager.controller;

import br.com.fatec.booksManager.exception.BadRequestExceptionHandler;
import br.com.fatec.booksManager.model.Book;
import br.com.fatec.booksManager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/livros")
public class BookController {

    @Autowired
    BookService service;

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Book>> findById(@PathVariable Long id) {
        Optional<Book> book = service.findById(id);
        if(book.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Optional<?>> create(@RequestBody Book book) {
        if(null != book.getId()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Optional.of("If you are trying to update informations from a book, use the method PUT instead."));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Optional.of(service.save(book)));
    }

    @PutMapping
    public ResponseEntity<Optional<?>> update(@RequestBody Book book) {
        if(null == book.getId()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Optional.of("If you are trying to update informations from a book, use the method PUT instead."));
        Optional<Book> foundBook = this.service.findById(book.getId());
        if( foundBook.isEmpty() ) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Optional.of("There is no book with this Id"));
        return ResponseEntity.ok(Optional.of(service.update(foundBook.get(), book)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<?>> updateWithId(@RequestBody Book book, @PathVariable Long id) {
        Optional<Book> foundBook = this.service.findById(id);
        if( foundBook.isEmpty() ) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Optional.of("There is no book with this Id"));
        return ResponseEntity.ok(Optional.of(service.update(foundBook.get(), book)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("The book with id: " + id + " has been deleted.");
    }

}
