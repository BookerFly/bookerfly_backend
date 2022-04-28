package ntut.edu.tw.bookerfly.entity.record;

import ntut.edu.tw.bookerfly.entity.collection.Book;
import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import ntut.edu.tw.bookerfly.entity.collection.BookStatus;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecordManagerTest {
    @Test
    public void create_a_check_out_record() {
        BookInformation bookInfo = new BookInformation("title", "author", "isbn", "image", "type");
        Book book = new Book(bookInfo.getBookInfoId(), BookStatus.AVAILABLE, "Lab1321", 1);
        book.setBookStatus(BookStatus.CHECKED_OUT);
        String userId = UUID.randomUUID().toString();

        RecordManager recordManager = new RecordManager();
        recordManager.createCheckOutRecord(book.getBookId(), userId);

        List<CheckOutRecord> checkOutRecordList = recordManager.getCheckOutRecordList();
        assertEquals(1, checkOutRecordList.size());
        assertEquals(book.getBookId(), checkOutRecordList.get(0).getBookId());
        assertEquals(BookStatus.CHECKED_OUT, checkOutRecordList.get(0).getBookStatus());
        assertEquals(userId, checkOutRecordList.get(0).getUserId());
    }
}