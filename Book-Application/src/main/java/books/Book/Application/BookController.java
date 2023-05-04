package books.Book.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping("/add")
    public ResponseEntity<Book> add(@RequestBody Book book) {
        Book rs = service.add(book);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @PostMapping("/add_and_send_msg")
    public ResponseEntity<Book> addAndSendMsg(@RequestBody Book book) {
        Book rs = service.addAndSendMsg(book);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Book> update(@RequestBody Book book) {
        Book rs = service.update(book);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> get(@PathVariable String isbn) {
        Book rs = service.get(isbn);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Book>> getAll() {
        List<Book> rs = service.getAll();
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam String isbn) {
        service.delete(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT, HttpStatus.OK);
    }

}
