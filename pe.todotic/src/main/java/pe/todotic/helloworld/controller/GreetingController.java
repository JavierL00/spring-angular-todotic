package pe.todotic.helloworld.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pe.todotic.helloworld.model.Book;

@RestController
@RequestMapping("/api")
public class GreetingController {
    @GetMapping("/hello")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    String greeting(@RequestParam(required = false) String name) {
        return (name != null) ? "Hola " + name + "!" : "Hola Mundo!";
    }

    @PostMapping("/books")
    @ResponseStatus(code = HttpStatus.CREATED)
    Book createBook(@RequestBody Book book) {
        return book;
    }

    @GetMapping("/books/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    Book getBook(@PathVariable Long id) {
        return new Book(id, "El Quijote", 190F);
    }
}
