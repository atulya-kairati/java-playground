package kairati.atulya.spring6webapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookServiceTest {

    public BookServiceTest() {
        System.out.println("constructor" + bookService);
    }

    @Autowired
    BookService bookService;

    @Test
    void findAll() {
        System.out.println(bookService.findAll());
    }
}

// jdbc
// hibernate
// jpa
