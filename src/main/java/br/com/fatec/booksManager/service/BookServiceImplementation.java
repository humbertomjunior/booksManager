package br.com.fatec.booksManager.service;

import br.com.fatec.booksManager.model.Book;
import br.com.fatec.booksManager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplementation implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public Book save(Book book) {
        return this.repository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Book update(Book book) {
        return this.repository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        try {
            this.repository.deleteById(id);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
