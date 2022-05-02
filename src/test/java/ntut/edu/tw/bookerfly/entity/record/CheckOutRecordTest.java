package ntut.edu.tw.bookerfly.entity.record;

import ntut.edu.tw.bookerfly.entity.collection.Book;
import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import ntut.edu.tw.bookerfly.entity.collection.BookStatus;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckOutRecordTest {
    @Test
    public void create_a_check_out_record() {
        BookInformation bookInfo = new BookInformation("title", "author", "isbn", "image", "type");
        Book book = new Book(bookInfo.getBookInfoId(), BookStatus.AVAILABLE, "Lab1321", 1);
        book.setBookStatus(BookStatus.CHECKED_OUT);
        String userId = UUID.randomUUID().toString();
        Instant timestamp = Instant.now();

        CheckOutRecord checkOutRecord = new CheckOutRecord(bookInfo.getTitle(), book.getBookId(), userId, book.getBookStatus(), timestamp);

        assertEquals(bookInfo.getTitle(), checkOutRecord.getBookTitle());
        assertEquals(book.getBookId(), checkOutRecord.getBookId());
        assertEquals(userId, checkOutRecord.getUserId());
        assertEquals(BookStatus.CHECKED_OUT, checkOutRecord.getBookStatus());
        assertEquals(timestamp, checkOutRecord.getTimestamp());
    }
}