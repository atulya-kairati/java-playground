package kairati.atulya.spring6webapp.bootstrap;

import kairati.atulya.spring6webapp.domain.Author;
import kairati.atulya.spring6webapp.domain.Book;
import kairati.atulya.spring6webapp.domain.Publisher;
import kairati.atulya.spring6webapp.repository.AuthorRepository;
import kairati.atulya.spring6webapp.repository.BookRepository;
import kairati.atulya.spring6webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            PublisherRepository publisherRepository
    ) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("In bootstrap");

        /**
         * Connections/Association can only be made between saved entities.
         * So first save all the entities,
         * then build associations
         * and then save them again.
         */

        // create entities
        Publisher slimeBooks = new Publisher("Slime Books", "271207");
        Publisher viva = new Publisher("Viva", "560100");

        Author watts = new Author("Peter", "Watts");
        Author dunbar = new Author("Robert", "Dunbar");

        Book blindsight = new Book("Blindsight", "34t433423e32");
        Book psychology = new Book("Evolutionary Psychology", "6875645754");

        // save entities to db
        Author wattsSaved = authorRepository.save(watts);
        Author dunbarSaved = authorRepository.save(dunbar);

        Book blindsightSaved = bookRepository.save(blindsight);
        Book psychologySaved = bookRepository.save(psychology);

        Publisher vivaSaved = publisherRepository.save(viva);
        Publisher slimeBooksSaved = publisherRepository.save(slimeBooks);


        // make associations between saved entities
        // author - book
        wattsSaved.getBooks().add(blindsightSaved);
        dunbarSaved.getBooks().add(psychologySaved);

        // book - author
        blindsightSaved.getAuthors().add(wattsSaved);
        psychologySaved.getAuthors().add(dunbarSaved);

        // book - publisher
        blindsightSaved.setPublisher(vivaSaved);
        psychologySaved.setPublisher(slimeBooksSaved);

        // publisher - book
        vivaSaved.getBooks().add(blindsightSaved);
        slimeBooksSaved.getBooks().add(psychologySaved);

        // finally save the associations
        authorRepository.save(wattsSaved);
        authorRepository.save(dunbarSaved);

        bookRepository.save(psychologySaved);
        bookRepository.save(blindsightSaved);

        publisherRepository.save(vivaSaved);
        publisherRepository.save(slimeBooksSaved);


        // log db row count
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
        System.out.println("Publisher count: " + publisherRepository.count());

    }
}
