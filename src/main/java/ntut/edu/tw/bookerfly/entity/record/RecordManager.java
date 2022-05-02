package ntut.edu.tw.bookerfly.entity.record;

import ntut.edu.tw.bookerfly.entity.collection.BookStatus;
import ntut.edu.tw.bookerfly.respository.CheckOutRecordRepository;
import java.time.Instant;
import java.util.List;

public class RecordManager {
    private CheckOutRecordRepository checkOutRecordRepository;
    private List<CheckOutRecord> checkOutRecords;

    public RecordManager(CheckOutRecordRepository checkOutRecordRepository) {
        this.checkOutRecordRepository = checkOutRecordRepository;
        checkOutRecords = checkOutRecordRepository.findAll();
    }

    public void createCheckOutRecord(String bookTitle, String bookId, String userId) {
        CheckOutRecord checkOutRecord = new CheckOutRecord(bookTitle, bookId, userId, BookStatus.CHECKED_OUT, Instant.now());
        checkOutRecords.add(checkOutRecord);
        checkOutRecordRepository.save(checkOutRecord);
    }

    public List<CheckOutRecord> getCheckOutRecordList() {
        return checkOutRecords;
    }
}