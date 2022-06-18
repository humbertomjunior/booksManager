package br.com.fatec.booksManager.controller;

import br.com.fatec.booksManager.model.Book;
import br.com.fatec.booksManager.service.BookService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
        if(null == book.getId()) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Optional.of(service.save(book)));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(Optional.of("If you are up to update a book, use the method PUT. Otherwise, if you are trying to add a new book, remove the 'id' field from the request body."));
        }
    }

    @PutMapping
    public ResponseEntity<Book> update(@RequestBody Book book) {
        return ResponseEntity.ok(service.update(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("The book with id: " + id + " has been deleted.");
    }

}
