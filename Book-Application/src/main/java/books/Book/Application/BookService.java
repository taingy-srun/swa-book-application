package books.Book.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    JmsSender jmsSender;

    public Book get(String isbn) {
        return repository.get(isbn);
    }

    public Book add(Book book) {
        return repository.add(book);
    }

    public Book addAndSendMsg(Book book) {
        Book book1 = repository.add(book);
        jmsSender.sendMessage(book1);
        return book1;
    }
    public Book update(Book book) {
        return repository.update(book);
    }

    public void delete(String isbn) {
        repository.delete(isbn);
    }

    public List<Book> getAll() {
        return repository.getAll();
    }
}
