package ntut.edu.tw.bookerfly.entity.collection;

import ntut.edu.tw.bookerfly.AbstractSpringJpaTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class CollectionTest extends AbstractSpringJpaTest {

    @Test
    public void create_a_book_in_collection() {
        collection.createBook("DDD", "author", ISBN, "image", "type", "Lab1321", 1, 1);

        BookInformation bookInformation = collection.getAllBookInformations().stream().filter(x -> x.getISBN().equals(ISBN)).findFirst().get();
        Optional<BookInformation> bookInfo = collection.getBookInformationById(bookInformation.getBookInfoId());
        assertTrue(bookInfo.isPresent());
        assertEquals("DDD", bookInfo.get().getTitle());
        assertEquals("author", bookInfo.get().getAuthor());
        assertEquals(ISBN, bookInfo.get().getISBN());
        assertEquals("image", bookInfo.get().getImage());
        assertEquals("type", bookInfo.get().getType());
        Book book = collection.getAllBooks().stream().filter(x -> x.getBookInfoId().equals(bookInformation.getBookInfoId())).findFirst().get();
        assertEquals(BookStatus.AVAILABLE, book.getBookStatus());
        assertEquals("Lab1321", book.getBookshelfPosition());
        assertEquals(1, book.getBookshelfNumber());
    }

    @Test
    public void create_a_book_with_existing_book_information_in_collection() {
        int originalCount = collection.getCount();
        collection.createBook("title", "author", ISBN, "image", "type", "Lab1321", 1, 1);

        collection.createBook("title", "author", ISBN, "image", "type", "Lab1324", 2, 1);

        int currentCount = collection.getCount();
        assertEquals(2, currentCount - originalCount);
        BookInformation bookInformation = collection.getAllBookInformations().stream().filter(x -> x.getISBN().equals(ISBN)).findFirst().get();
        Book book = collection.getAllBooks().stream().filter(x -> x.getBookInfoId().equals(bookInformation.getBookInfoId()) && x.getBookshelfPosition().equals("Lab1324")).findFirst().get();
        assertEquals(bookInformation.getBookInfoId(), book.getBookInfoId());
        assertEquals(BookStatus.AVAILABLE, book.getBookStatus());
        assertEquals("Lab1324", book.getBookshelfPosition());
        assertEquals(2, book.getBookshelfNumber());
    }

    @Test
    public void create_three_books_with_same_book_information_in_collection() {
        int originalCount = collection.getCount();

        collection.createBook("title", "author", ISBN, "image", "type", "Lab1321", 1, 3);

        int currentCount = collection.getCount();
        assertEquals(3, currentCount - originalCount);
        BookInformation bookInformation = collection.getAllBookInformations().stream().filter(x -> x.getISBN().equals(ISBN)).findFirst().get();
        List<Book> books = collection.selectBook(bookInformation.getBookInfoId());
        assertEquals(books.get(0).getBookInfoId(), books.get(1).getBookInfoId());
    }

    @Test
    public void select_three_books_in_collection() {
        collection.createBook("title", "author", ISBN, "image", "type", "Lab1321", 1, 3);
        collection.createBook("title2", "author", ISBN, "image", "type", "Lab1321", 1, 2);
        BookInformation bookInformation = collection.getAllBookInformations().stream().filter(x -> x.getISBN().equals(ISBN)).findFirst().get();

        List<Book> searchResult = collection.selectBook(bookInformation.getBookInfoId());

        assertEquals(3, searchResult.size());
    }

    @Test
    public void borrow_book() {
        String ISBN2 = UUID.randomUUID().toString();
        collection.createBook("title", "author", ISBN, "image", "type", "Lab1321", 1, 3);
        collection.createBook("design patterns", "gof", ISBN2, "", "Book", "Lab1321", 1, 2);
        BookInformation bookInformation = collection.getAllBookInformations().stream().filter(x -> x.getISBN().equals(ISBN2)).findFirst().get();
        Book book = collection.getAllBooks().stream().filter(x -> x.getBookInfoId().equals(bookInformation.getBookInfoId())).findFirst().get();

        collection.borrowBook(book.getBookId());

        assertEquals("design patterns", collection.getBookInformationById(book.getBookInfoId()).get().getTitle());
        assertEquals(BookStatus.CHECKED_OUT, book.getBookStatus());
    }

    @Test
    public void borrow_a_checked_out_book() {
        String ISBN2 = UUID.randomUUID().toString();
        collection.createBook("title", "author", ISBN, "image", "type", "Lab1321", 1, 3);
        collection.createBook("design patterns", "gof", ISBN2, "", "Book", "Lab1321", 1, 2);
        BookInformation bookInformation = collection.getAllBookInformations().stream().filter(x -> x.getISBN().equals(ISBN2)).findFirst().get();
        Book book = collection.getAllBooks().stream().filter(x -> x.getBookInfoId().equals(bookInformation.getBookInfoId())).findFirst().get();
        collection.borrowBook(book.getBookId());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            collection.borrowBook(book.getBookId());
        });

        assertEquals("The book is already checked out.", exception.getMessage());
    }
}