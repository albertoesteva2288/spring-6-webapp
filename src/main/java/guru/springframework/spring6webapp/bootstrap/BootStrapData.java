package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repository.AuthorRepository;
import guru.springframework.spring6webapp.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Step 1: Create de objects to persist in DB
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");
        // Step 2: The owner book needs an author in the db (needs the author id) before to be persisted, so ....
        authorRepository.save(eric); // Now eric is a managed object and have an id
        // Step 3: Add the author to the set of authors of the book
        // I modify the set method to an add method to add only one author at once
        // DON'T FORGET INITIALIZE THE SET OR YOU'LL GET AN ERROR
        ddd.addAuthor(eric);
        // Step 4: Save the book
        bookRepository.save(ddd); // Now ddd is a managed object with an id too
        // Step 5: Update the author
        // Because author and book now are managed, they can be updated in the db using setter methods
        // Of course... just until the end of method
        eric.addBook(ddd);

        authorRepository.save(rod);
        noEJB.addAuthor(rod);
        bookRepository.save(noEJB);
        rod.addBook(noEJB);

        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + authorRepository.count());



    }
}
