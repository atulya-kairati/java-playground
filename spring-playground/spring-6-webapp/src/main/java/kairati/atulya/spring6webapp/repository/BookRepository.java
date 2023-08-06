package kairati.atulya.spring6webapp.repository;

import kairati.atulya.spring6webapp.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
