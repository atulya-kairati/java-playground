package kairati.atulya.spring6webapp.service;

import kairati.atulya.spring6webapp.domain.Book;

public interface BookService {
    Iterable<Book> findAll();

}
