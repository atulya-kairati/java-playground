package kairati.atulya.spring6webapp.service;

import kairati.atulya.spring6webapp.domain.Book;
import kairati.atulya.spring6webapp.repository.BookRepository;
import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }
}
