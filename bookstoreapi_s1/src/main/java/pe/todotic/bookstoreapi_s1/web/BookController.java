package pe.todotic.bookstoreapi_s1.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pe.todotic.bookstoreapi_s1.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/books")
public class BookController {
    public static List<Book> bookList = new ArrayList<>();

    /**
     * Devuelve la lista completa de libros
     * Retorna el status OK: 200
     * Ej.: GET http://localhost:9090/api/books
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Book> list() {
        return bookList;
    }

    /**
     * Devuelve un libro por su ID, en caso contrario null.
     * Retorna el status OK: 200
     * Ej.: GET http://localhost:9090/api/books/1
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Book get(@PathVariable Integer id) {
        return bookList
                .stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Crea un libro a partir del cuerpo
     * de la solicitud HTTP y retorna
     * el libro creado.
     * Retorna el status CREATED: 201
     * Ej.: POST http://localhost:9090/api/books
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Book create(@RequestBody Book book) {
        book.setId(new Random().nextInt(256));
        bookList.add(book);
        return book;
    }

    /**
     * Actualiza un libro por su ID, a partir
     * del cuerpo de la solicitud HTTP.
     * Retorna el status OK: 200.
     * Ej.: PUT http://localhost:9090/api/books/1
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Book update(@PathVariable Integer id,
            @RequestBody Book bookForm) {
        Book bookFromDb = bookList
                .stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (bookFromDb == null) return null;

        bookFromDb.setTitle(bookForm.getTitle());
        bookFromDb.setPrice(bookForm.getPrice());

        return bookFromDb;
    }

    /**
     * Elimina un libro por su ID.
     * Retorna el status NO_CONTENT: 204
     * Ej.: DELETE http://localhost:9090/api/books/1
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Integer id) {
        bookList.removeIf(l -> l.getId().equals(id));
    }
}
