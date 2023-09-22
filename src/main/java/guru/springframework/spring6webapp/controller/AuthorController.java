package guru.springframework.spring6webapp.controller;

import guru.springframework.spring6webapp.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
@RequestMapping("/authors")
    public String listAuthors(Model model){
        model.addAttribute("lstAuthors", authorService.findAll());
        return "authors";
    }
}
