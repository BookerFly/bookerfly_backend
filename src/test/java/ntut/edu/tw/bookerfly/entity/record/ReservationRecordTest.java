package ntut.edu.tw.bookerfly.entity.record;

import ntut.edu.tw.bookerfly.AbstractSpringJpaTest;
import ntut.edu.tw.bookerfly.entity.collection.Book;
import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import ntut.edu.tw.bookerfly.entity.collection.BookStatus;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationRecordTest extends AbstractSpringJpaTest {
    @Test
    public void reserve_a_book() {
        BookInformation bookInfo = new BookInformation("title", "author", "isbn", "image", "type");
        Book book = new Book(bookInfo, BookStatus.AVAILABLE, "Lab1321", 1);
        book.setBookStatus(BookStatus.CHECKED_OUT);
        String userId = UUID.randomUUID().toString();
        Instant timestamp = Instant.now();

        ReservationRecord reservationRecord = new ReservationRecord(book.getBookId(), userId, timestamp);

        assertEquals(book.getBookId(), reservationRecord.getBookId());
        assertEquals(userId, reservationRecord.getUserId());
        assertEquals(timestamp, reservationRecord.getTimestamp());
    }
}
