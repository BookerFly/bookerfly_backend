package ntut.edu.tw.bookerfly.entity.collection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    @Test
    public void create_a_book() {
        BookInformation bookInfo = new BookInformation("OOAD", "author", "isbn", "image", "type");

        Book book = new Book(bookInfo, BookStatus.AVAILABLE, "Lab1321", 1);

        assertNotNull(book.getBookId());
        assertEquals(bookInfo.getTitle(), book.getTitle());
        assertEquals(bookInfo.getAuthor(), book.getAuthor());
        assertEquals(bookInfo.getISBN(), book.getISBN());
        assertEquals(bookInfo.getImage(), book.getImage());
        assertEquals(bookInfo.getType(), book.getType());
        assertEquals(BookStatus.AVAILABLE, book.getBookStatus());
        assertEquals("Lab1321", book.getBookshelfPosition());
        assertEquals(1, book.getBookshelfNumber());
    }

    @Test
    public void set_bookshelf_position() {
        BookInformation bookInfo = new BookInformation("title", "author", "isbn", "image", "type");
        Book book = new Book(bookInfo, BookStatus.AVAILABLE, "Lab1321", 1);

        book.setBookshelfPosition("Lab1424");

        assertEquals("Lab1424", book.getBookshelfPosition());
    }

    @Test
    public void set_bookshelf_number() {
        BookInformation bookInfo = new BookInformation("title", "author", "isbn", "image", "type");
        Book book = new Book(bookInfo, BookStatus.AVAILABLE, "Lab1321", 1);

        book.setBookshelfNumber(2);

        assertEquals(2, book.getBookshelfNumber());
    }

    @Test
    public void set_book_status() {
        BookInformation bookInfo = new BookInformation("title", "author", "isbn", "image", "type");
        Book book = new Book(bookInfo, BookStatus.AVAILABLE, "Lab1321", 1);

        book.setBookStatus(BookStatus.CHECKED_OUT);

        assertEquals(BookStatus.CHECKED_OUT, book.getBookStatus());
    }

    @Test
    public void book_has_same_information_by_checking_information_id() {
        BookInformation bookInfo = new BookInformation("title", "author", "isbn", "image", "type");
        Book book = new Book(bookInfo, BookStatus.AVAILABLE, "Lab1321", 1);

        assertTrue(book.hasSameBookInfo(bookInfo.getBookInfoId()));
    }

    @Test
    public void book_has_same_information_by_checking_information_detail() {
        BookInformation bookInfo = new BookInformation("title", "author", "isbn", "image", "type");
        Book book = new Book(bookInfo, BookStatus.AVAILABLE, "Lab1321", 1);

        assertTrue(book.hasSameBookInfo("title", "author", "isbn", "image", "type"));
    }
}