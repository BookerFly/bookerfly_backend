package ntut.edu.tw.bookerfly.entity.record;

import ntut.edu.tw.bookerfly.entity.collection.BookStatus;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class RecordManager {
    private List<CheckOutRecord> checkOutRecords;

    public RecordManager() {
        checkOutRecords = new ArrayList<>();
    }

    public void createCheckOutRecord(String bookId, String userId) {
        CheckOutRecord checkOutRecord = new CheckOutRecord(bookId, userId, BookStatus.CHECKED_OUT, Instant.now());
        checkOutRecords.add(checkOutRecord);
    }

    public List<CheckOutRecord> getCheckOutRecordList() {
        return checkOutRecords;
    }
}