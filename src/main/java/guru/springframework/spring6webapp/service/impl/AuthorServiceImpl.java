package guru.springframework.spring6webapp.service.impl;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.repository.AuthorRepository;
import guru.springframework.spring6webapp.service.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Iterable<Author> findAll() {
        return authorRepository.findAll();
    }
}
