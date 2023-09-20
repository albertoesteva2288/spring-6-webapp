package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repository.AuthorRepository;
import guru.springframework.spring6webapp.repository.BookRepository;
import guru.springframework.spring6webapp.repository.PublisherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
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

        Publisher wileyPublisher = new Publisher();
        wileyPublisher.setPublisherName("Wiley Publishing, Inc");
        wileyPublisher.setAddress("Crosspoint Blvd");
        wileyPublisher.setCity("Indianapolis");
        wileyPublisher.setState("Indiana");
        wileyPublisher.setZip("10475");

        Publisher adisonPublisher = new Publisher();
        adisonPublisher.setPublisherName("Adison Wesley");
        adisonPublisher.setAddress("Boylston Street");
        adisonPublisher.setCity("Boston");
        adisonPublisher.setState("Massachusetts");
        adisonPublisher.setZip("02116");

        publisherRepository.save(wileyPublisher);
        publisherRepository.save(adisonPublisher);


        logger.info("Author count: {}" , authorRepository.count());
        logger.info("Book count: {}" , authorRepository.count());
        logger.info("Publisher count: {}" , publisherRepository.count());




    }
}
