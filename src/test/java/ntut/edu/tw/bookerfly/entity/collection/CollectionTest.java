package ntut.edu.tw.bookerfly.entity.collection;

import ntut.edu.tw.bookerfly.AbstractSpringJpaTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

public class CollectionTest extends AbstractSpringJpaTest {
    @Test
    public void create_a_book_in_collection() {
        collection.createBook("DDD", "author", ISBN, "image", "type", "Lab1321", 1, 1);

        Book book = collection.getAllBooks().get(collection.getCount() - 1);
        assertEquals("DDD", book.getTitle());
        assertEquals("author", book.getAuthor());
        assertEquals(ISBN, book.getISBN());
        assertEquals("image", book.getImage());
        assertEquals("type", book.getType());
        assertEquals(BookStatus.AVAILABLE, book.getBookStatus());
        assertEquals("Lab1321", book.getBookshelfPosition());
        assertEquals(1, book.getBookshelfNumber());
    }

    @Test
    public void create_a_book_with_existing_book_information_in_collection() {
        collection.createBook("POSD", "author", ISBN, "image", "type", "Lab1321", 1, 1);

        collection.createBook("POSD", "author", ISBN, "image", "type", "Lab1324", 2, 1);

        Book book1 = collection.getAllBooks().get(collection.getCount() - 2);
        Book book2 = collection.getAllBooks().get(collection.getCount() - 1);
        assertTrue(book1.hasSameBookInfo(book2.getTitle(), book2.getAuthor(), book2.getISBN(), book2.getImage(), book2.getType()));
        assertEquals(BookStatus.AVAILABLE, book2.getBookStatus());
        assertEquals("Lab1324", book2.getBookshelfPosition());
        assertEquals(2, book2.getBookshelfNumber());
    }

    @Test
    public void create_two_books_with_same_book_information_in_collection() {
        collection.createBook("title", "author", ISBN, "image", "type", "Lab1321", 1, 2);

        List<Book> books = collection.getAllBooks().stream().filter(x -> x.hasSameBookInfo("title", "author", ISBN, "image", "type")).collect(Collectors.toList());
        assertEquals(2, books.size());
        assertTrue(books.get(0).hasSameBookInfo(books.get(1).getTitle(), books.get(1).getAuthor(), books.get(1).getISBN(), books.get(1).getImage(), books.get(1).getType()));
    }

    @Test
    public void select_three_books_in_collection() {
        collection.createBook("title", "author", ISBN, "image", "type", "Lab1321", 1, 3);
        collection.createBook("title2", "author2", ISBN, "image2", "type2", "Lab1321", 1, 2);

        List<Book> books = collection.selectBook("title", "author", ISBN, "image", "type");

        assertEquals(3, books.size());
    }

    @Test
    public void borrow_book() {
        String ISBN2 = UUID.randomUUID().toString();
        collection.createBook("Software Testing", "author", ISBN, "image", "type", "Lab1321", 1, 3);
        collection.createBook("design patterns", "gof", ISBN2, "", "Book", "Lab1321", 1, 2);
        Book book = collection.selectBook("design patterns", "gof", ISBN2, "", "Book").get(0);

        collection.borrowBook(book.getBookId());

        assertEquals("design patterns", book.getTitle());
        assertEquals(BookStatus.CHECKED_OUT, book.getBookStatus());
    }

    @Test
    public void borrow_a_checked_out_book() {
        String ISBN2 = UUID.randomUUID().toString();
        collection.createBook("title", "author", ISBN, "image", "type", "Lab1321", 1, 3);
        collection.createBook("design patterns", "gof", ISBN2, "", "Book", "Lab1321", 1, 2);
        Book book = collection.selectBook("design patterns", "gof", ISBN2, "", "Book").get(0);
        collection.borrowBook(book.getBookId());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            collection.borrowBook(book.getBookId());
        });

        assertEquals("The book is already checked out.", exception.getMessage());
    }

    @Test
    public void return_book() {
        String ISBN2 = UUID.randomUUID().toString();
        collection.createBook("title", "author", ISBN, "image", "type", "Lab1321", 1, 3);
        collection.createBook("design patterns", "gof", ISBN2, "", "Book", "Lab1321", 1, 2);
        Book book = collection.selectBook("design patterns", "gof", ISBN2, "", "Book").get(0);
        collection.borrowBook(book.getBookId());

        collection.returnBook(book.getBookId());

        assertEquals(BookStatus.PROCESSING, book.getBookStatus());
    }
}