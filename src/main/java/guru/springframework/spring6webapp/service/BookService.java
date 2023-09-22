package guru.springframework.spring6webapp.service;

import guru.springframework.spring6webapp.domain.Book;

import java.util.Set;

public interface BookService {

    Iterable<Book> findAll();
}
