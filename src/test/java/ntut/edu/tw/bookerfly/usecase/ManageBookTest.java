package ntut.edu.tw.bookerfly.usecase;

import ntut.edu.tw.bookerfly.AbstractSpringJpaTest;
import ntut.edu.tw.bookerfly.entity.collection.Book;
import ntut.edu.tw.bookerfly.entity.collection.BookStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManageBookTest extends AbstractSpringJpaTest {
    @Test
    public void add_books() {
        collection.createBook("title", "author", ISBN, "image", "type", "Lab1321", 1, 3);
        collection.createBook("title2", "author2", ISBN, "image2", "type2", "Lab1324", 1, 2);

        assertEquals(5, collection.getCount());
    }

    @Test
    public void edit_a_book_since_rearrange() {
        collection.createBook("title", "author", ISBN, "image", "type", "Lab1321", 1, 3);
        Book book = collection.selectBook("title", "author", ISBN, "image", "type").get(0);

        collection.editBook(book.getBookId(), "Lab1424", 2, BookStatus.AVAILABLE);

        assertEquals("Lab1424", book.getBookshelfPosition());
        assertEquals(2, book.getBookshelfNumber());
    }
}
