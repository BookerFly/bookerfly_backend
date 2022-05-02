package ntut.edu.tw.bookerfly.entity.collection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    @Test
    public void create_a_book() {
        BookInformation bookInfo = new BookInformation("OOAD", "author", "isbn", "image", "type");

        Book book = new Book(bookInfo.getBookInfoId(), BookStatus.AVAILABLE, "Lab1321", 1);

        assertNotNull(book.getBookId());
        assertEquals(bookInfo.getBookInfoId(), book.getBookInfoId());
        assertEquals(BookStatus.AVAILABLE, book.getBookStatus());
        assertEquals("Lab1321", book.getBookshelfPosition());
        assertEquals(1, book.getBookshelfNumber());
    }

    @Test
    public void set_book_status() {
        BookInformation bookInfo = new BookInformation("title", "author", "isbn", "image", "type");
        Book book = new Book(bookInfo.getBookInfoId(), BookStatus.AVAILABLE, "Lab1321", 1);

        book.setBookStatus(BookStatus.CHECKED_OUT);

        assertEquals(BookStatus.CHECKED_OUT, book.getBookStatus());
    }
}