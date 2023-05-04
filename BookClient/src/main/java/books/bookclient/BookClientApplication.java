package books.bookclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class BookClientApplication implements CommandLineRunner {

    final String BASE_URL = "http://localhost:8080/book";

    @Autowired
    private RestOperations restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(BookClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        restTemplate.postForLocation(BASE_URL + "/add", new Book("123-123", "Unknown", "The Book", 12.99));
        restTemplate.postForLocation(BASE_URL + "/add", new Book("123-125", "Unknown", "The Book with...", 11.99));

        List<Book> books = (List<Book>) restTemplate.getForObject(BASE_URL + "/get_all", Collection.class);
        System.out.println(books);

        Book book = restTemplate.getForEntity(BASE_URL + "/{isbn}", Book.class, "123-123").getBody();
        System.out.println(book);

        book.setPrice(11.56);
        restTemplate.postForLocation(BASE_URL + "/update", book);
        restTemplate.delete(BASE_URL + "/delete?isbn={isbn}",  "123-125");

        List<Book> booksAfterDelete = (List<Book>) restTemplate.getForObject(BASE_URL + "/get_all", Collection.class);
        System.out.println(booksAfterDelete);
    }

    @Bean
    RestOperations restTemplate() {
        return new RestTemplate();
    }
}
