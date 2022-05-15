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

    public void updateCheckOutRecord(String bookId, String userId, BookStatus bookStatus) {
        List<CheckOutRecord> checkOutRecords = getCheckOutRecord(bookId, userId);
        if(checkOutRecords.size() != 0) {
            CheckOutRecord checkOutRecord = checkOutRecords.get(checkOutRecords.size() - 1);
            checkOutRecord.updateBookStatus(bookStatus, Instant.now());
            checkOutRecordRepository.save(checkOutRecord);
        }
    }

    public List<CheckOutRecord> getCheckOutRecordList() {
        return checkOutRecords;
    }

    private List<CheckOutRecord> getCheckOutRecord(String bookId, String userId) {
        return checkOutRecords.stream().filter(x -> x.getBookId().equals(bookId) && x.getUserId().equals(userId)).toList();
    }
}