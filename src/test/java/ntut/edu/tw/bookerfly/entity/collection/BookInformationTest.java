package ntut.edu.tw.bookerfly.entity.collection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookInformationTest {
    @Test
    public void create_a_book_information() {
        BookInformation bookInfo = new BookInformation("SE", "author", "isbn", "image", "type");

        assertNotNull(bookInfo.getBookInfoId());
        assertEquals("SE", bookInfo.getTitle());
        assertEquals("author", bookInfo.getAuthor());
        assertEquals("isbn", bookInfo.getISBN());
        assertEquals("image", bookInfo.getImage());
        assertEquals("type", bookInfo.getType());
    }
}