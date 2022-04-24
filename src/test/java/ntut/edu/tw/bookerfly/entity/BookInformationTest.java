package ntut.edu.tw.bookerfly.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookInformationTest {
    @Test
    public void create_a_book_information() {
        BookInformation bookInfo = new BookInformation("title", "author", "isbn", "image", "type");

        assertNotNull(bookInfo.getBookInfoId());
        assertEquals("title", bookInfo.getTitle());
        assertEquals("author", bookInfo.getAuthor());
        assertEquals("isbn", bookInfo.getISBN());
        assertEquals("image", bookInfo.getImage());
        assertEquals("type", bookInfo.getType());
    }
}