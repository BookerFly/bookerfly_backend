package ntut.edu.tw.bookerfly.entity.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class CollectionTest {
    @Test
    public void create_a_empty_collection() {
        Collection collection = new Collection();

        assertEquals(0, collection.getCount());
        assertTrue(collection.getAllBooks().isEmpty());
    }

    @Test
    public void create_a_book_in_collection() {
        Collection collection = new Collection();

        collection.createBook("title", "author", "isbn", "image", "type", "Lab1321", 1, 1);

        assertEquals(1, collection.getCount());
        Book book = collection.getAllBooks().get(0);
        Optional<BookInformation> bookInfo = collection.getBookInformationById(book.getBookInfoId());
        assertTrue(bookInfo.isPresent());
        assertEquals("title", bookInfo.get().getTitle());
        assertEquals("author", bookInfo.get().getAuthor());
        assertEquals("isbn", bookInfo.get().getISBN());
        assertEquals("image", bookInfo.get().getImage());
        assertEquals("type", bookInfo.get().getType());
        assertEquals(BookStatus.AVAILABLE, book.getBookStatus());
        assertEquals("Lab1321", book.getBookshelfPosition());
        assertEquals(1, book.getBookshelfNumber());
    }

    @Test
    public void create_a_book_with_existing_book_information_in_collection() {
        Collection collection = new Collection();
        collection.createBook("title", "author", "isbn", "image", "type", "Lab1321", 1, 1);

        collection.createBook("title", "author", "isbn", "image", "type", "Lab1324", 2, 1);

        assertEquals(2, collection.getCount());
        Book book = collection.getAllBooks().get(1);
        assertEquals(collection.getAllBooks().get(0).getBookInfoId(), book.getBookInfoId());
        assertEquals(BookStatus.AVAILABLE, book.getBookStatus());
        assertEquals("Lab1324", book.getBookshelfPosition());
        assertEquals(2, book.getBookshelfNumber());
    }

    @Test
    public void create_three_books_in_collection() {
        Collection collection = new Collection();

        collection.createBook("title", "author", "isbn", "image", "type", "Lab1321", 1, 3);

        assertEquals(3, collection.getCount());
        assertEquals(collection.getAllBooks().get(0).getBookInfoId(), collection.getAllBooks().get(1).getBookInfoId());
    }

    @Test
    public void select_three_books_in_collection() {
        Collection collection = new Collection();
        collection.createBook("title", "author", "isbn", "image", "type", "Lab1321", 1, 3);
        collection.createBook("title2", "author", "isbn", "image", "type", "Lab1321", 1, 2);

        List<Book> searchResult = collection.selectBook(collection.getAllBookInformations().get(0).getBookInfoId());

        assertEquals(3, searchResult.size());
    }

    @Test
    public void borrow_book() {
        Collection collection = new Collection();
        collection.createBook("title", "author", "isbn", "image", "type", "Lab1321", 1, 3);
        collection.createBook("design patterns", "gof", "isbn2", "", "Book", "Lab1321", 1, 2);
        Book book = collection.getAllBooks().get(4);

        collection.borrowBook(book.getBookId());

        assertEquals("design patterns", collection.getBookInformationById(book.getBookInfoId()).get().getTitle());
        assertEquals(BookStatus.CHECKED_OUT, book.getBookStatus());
    }

    @Test
    public void borrow_a_checked_out_book() {
        Collection collection = new Collection();
        collection.createBook("title", "author", "isbn", "image", "type", "Lab1321", 1, 3);
        collection.createBook("design patterns", "gof", "isbn2", "", "Book", "Lab1321", 1, 2);
        Book book = collection.getAllBooks().get(4);
        collection.borrowBook(book.getBookId());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            collection.borrowBook(book.getBookId());
        });

        assertEquals("The book is already checked out.", exception.getMessage());
    }
}