package ntut.edu.tw.bookerfly.entity.record;

import ntut.edu.tw.bookerfly.AbstractSpringJpaTest;
import ntut.edu.tw.bookerfly.entity.collection.Book;
import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import ntut.edu.tw.bookerfly.entity.collection.BookStatus;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}