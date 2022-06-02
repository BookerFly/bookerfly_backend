package ntut.edu.tw.bookerfly.usecase;

import ntut.edu.tw.bookerfly.AbstractSpringJpaTest;
import ntut.edu.tw.bookerfly.entity.collection.Book;
import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import ntut.edu.tw.bookerfly.entity.collection.BookStatus;
import ntut.edu.tw.bookerfly.entity.record.ReservationRecord;
import ntut.edu.tw.bookerfly.entity.user.Borrower;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReserveBookTest extends AbstractSpringJpaTest {
    @Test
    public void borrower_reserve_a_book() {
        BookInformation bookInfo = new BookInformation("title", "author", "isbn", "image", "type");
        Book book = new Book(bookInfo, BookStatus.AVAILABLE, "Lab1321", 1);
        book.setBookStatus(BookStatus.CHECKED_OUT);
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId, "Jay", "bookerfly.csie.ntut@gmail.com");

        recordManager.createReservationRecord(book.getBookId(), borrower.getUserId());

        List<ReservationRecord> reservationRecordList = recordManager.getReservationRecordList();
        ReservationRecord reservationRecord = reservationRecordList.get(reservationRecordList.size() - 1);
        assertEquals(book.getBookId(), reservationRecord.getBookId());
        assertEquals(borrower.getUserId(), reservationRecord.getUserId());
        assertTrue(recordManager.getReserver(book.getBookId()).isPresent());
        assertEquals(borrower.getUserId(), recordManager.getReserver(book.getBookId()).get());
    }
}
