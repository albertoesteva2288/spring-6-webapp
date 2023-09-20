package guru.springframework.spring6webapp.repository;

import guru.springframework.spring6webapp.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface BookRepository extends CrudRepository<Book, Long> {
}
