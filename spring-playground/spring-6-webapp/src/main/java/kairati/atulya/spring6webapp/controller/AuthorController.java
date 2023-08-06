package kairati.atulya.spring6webapp.controller;


import kairati.atulya.spring6webapp.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String getAuthor(Model model){
        model.addAttribute("authors", authorService.findAll());

        return "authors";
    }
}
