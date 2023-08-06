package kairati.atulya.spring6webapp.service;

import kairati.atulya.spring6webapp.domain.Author;

public interface AuthorService {

    Iterable<Author> findAll();
}
