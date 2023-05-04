package books.Book.Application;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepository {

    private TreeMap<String, Book> map = new TreeMap<>();

    public Book add(Book book) {
        map.put(book.getIsbn(), book);
        return get(book.getIsbn());
    }

    public Book get(String isbn) {
        return map.get(isbn);
    }

    public Book update(Book book) {
        map.put(book.getIsbn(), book);
        return get(book.getIsbn());
    }

    public void delete(String isbn) {
        map.remove(isbn);
    }

    public List<Book> getAll() {
        return map.values().stream().toList();
    }

}
