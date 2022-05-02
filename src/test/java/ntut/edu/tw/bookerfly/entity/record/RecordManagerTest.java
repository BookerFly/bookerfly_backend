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
        Book book = new Book(bookInfo.getBookInfoId(), BookStatus.AVAILABLE, "Lab1321", 1);
        book.setBookStatus(BookStatus.CHECKED_OUT);
        String userId = UUID.randomUUID().toString();
        int originalCount = recordManager.getCheckOutRecordList().size();

        recordManager.createCheckOutRecord(book.getBookId(), userId);

        int currentCount = recordManager.getCheckOutRecordList().size();
        List<CheckOutRecord> checkOutRecordList = recordManager.getCheckOutRecordList();
        assertEquals(1, currentCount - originalCount);
        CheckOutRecord checkOutRecord = checkOutRecordList.get(currentCount-1);
        assertEquals(book.getBookId(), checkOutRecord.getBookId());
        assertEquals(BookStatus.CHECKED_OUT, checkOutRecord.getBookStatus());
        assertEquals(userId, checkOutRecord.getUserId());
    }
}