package ntut.edu.tw.bookerfly.entity.record;

import ntut.edu.tw.bookerfly.AbstractSpringJpaTest;
import ntut.edu.tw.bookerfly.entity.collection.Book;
import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import ntut.edu.tw.bookerfly.entity.collection.BookStatus;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class RecordManagerTest extends AbstractSpringJpaTest {
    @Test
    public void create_a_check_out_record() {
        BookInformation bookInfo = new BookInformation("title", "author", "isbn", "image", "type");
        Book book = new Book(bookInfo, BookStatus.AVAILABLE, "Lab1321", 1);
        book.setBookStatus(BookStatus.CHECKED_OUT);
        String userId = UUID.randomUUID().toString();

        recordManager.createCheckOutRecord(bookInfo.getTitle(), book.getBookId(), userId);

        List<CheckOutRecord> checkOutRecordList = recordManager.getCheckOutRecordList();
        CheckOutRecord checkOutRecord = checkOutRecordList.get(checkOutRecordList.size() - 1);
        assertEquals(book.getBookId(), checkOutRecord.getBookId());
        assertEquals(BookStatus.CHECKED_OUT, checkOutRecord.getBookStatus());
        assertEquals(userId, checkOutRecord.getUserId());
    }

    @Test
    public void update_check_out_record() {
        BookInformation bookInfo = new BookInformation("title", "author", "isbn", "image", "type");
        Book book = new Book(bookInfo, BookStatus.AVAILABLE, "Lab1321", 1);
        book.setBookStatus(BookStatus.CHECKED_OUT);
        String userId = UUID.randomUUID().toString();
        recordManager.createCheckOutRecord(bookInfo.getTitle(), book.getBookId(), userId);

        recordManager.updateCheckOutRecord(book.getBookId(), userId, BookStatus.AVAILABLE);

        List<CheckOutRecord> checkOutRecordList = recordManager.getCheckOutRecordList();
        CheckOutRecord checkOutRecord = checkOutRecordList.get(checkOutRecordList.size() - 1);
        assertEquals(BookStatus.AVAILABLE, checkOutRecord.getBookStatus());
    }

    @Test
    public void create_a_reservation_record() {
        BookInformation bookInfo = new BookInformation("title", "author", "isbn", "image", "type");
        Book book = new Book(bookInfo, BookStatus.AVAILABLE, "Lab1321", 1);
        book.setBookStatus(BookStatus.CHECKED_OUT);
        String userId = UUID.randomUUID().toString();

        recordManager.createReservationRecord(book.getBookId(), userId);

        List<ReservationRecord> reservationRecordList = recordManager.getReservationRecordList();
        ReservationRecord reservationRecord = reservationRecordList.get(reservationRecordList.size() - 1);
        assertEquals(book.getBookId(), reservationRecord.getBookId());
        assertEquals(userId, reservationRecord.getUserId());
    }

    @Test
    public void get_a_reserver() {
        BookInformation bookInfo = new BookInformation("title", "author", "isbn", "image", "type");
        Book book = new Book(bookInfo, BookStatus.AVAILABLE, "Lab1321", 1);
        book.setBookStatus(BookStatus.CHECKED_OUT);
        String userId = UUID.randomUUID().toString();
        String userId2 = UUID.randomUUID().toString();
        String userId3 = UUID.randomUUID().toString();
        recordManager.createCheckOutRecord(bookInfo.getTitle(), book.getBookId(), userId);
        recordManager.createReservationRecord(book.getBookId(), userId2);
        recordManager.createReservationRecord(book.getBookId(), userId3);
        recordManager.updateCheckOutRecord(book.getBookId(), userId, BookStatus.AVAILABLE);

        Optional<String> reserverId = recordManager.getReserver(book.getBookId());

        assertTrue(reserverId.isPresent());
        assertEquals(userId2, reserverId.get());
    }

    @Test
    public void get_no_reserver() {
        BookInformation bookInfo = new BookInformation("title", "author", "isbn", "image", "type");
        Book book = new Book(bookInfo, BookStatus.AVAILABLE, "Lab1321", 1);
        book.setBookStatus(BookStatus.CHECKED_OUT);
        String userId = UUID.randomUUID().toString();
        recordManager.createCheckOutRecord(bookInfo.getTitle(), book.getBookId(), userId);
        recordManager.updateCheckOutRecord(book.getBookId(), userId, BookStatus.AVAILABLE);

        Optional<String> reserverId = recordManager.getReserver(book.getBookId());

        assertFalse(reserverId.isPresent());
    }
}