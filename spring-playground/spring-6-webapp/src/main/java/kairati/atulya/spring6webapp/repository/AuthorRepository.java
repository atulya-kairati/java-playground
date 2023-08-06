package kairati.atulya.spring6webapp.repository;

import kairati.atulya.spring6webapp.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
