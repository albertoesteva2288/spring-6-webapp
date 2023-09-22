package guru.springframework.spring6webapp.controller;

import guru.springframework.spring6webapp.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
@RequestMapping("/books")
    public String listBooks(Model model){
        model.addAttribute("lstBooks", bookService.findAll());
        return "books";
    }
}
