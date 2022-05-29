package ntut.edu.tw.bookerfly.entity.record;

import ntut.edu.tw.bookerfly.entity.collection.BookStatus;
import ntut.edu.tw.bookerfly.respository.record.CheckOutRecordRepository;
import ntut.edu.tw.bookerfly.respository.record.ReservationRecordRepository;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RecordManager {
    private CheckOutRecordRepository checkOutRecordRepository;
    private ReservationRecordRepository reservationRecordRepository;
    private List<CheckOutRecord> checkOutRecords;
    private List<ReservationRecord> reservationRecords;

    public RecordManager(CheckOutRecordRepository checkOutRecordRepository, ReservationRecordRepository reservationRecordRepository) {
        this.checkOutRecordRepository = checkOutRecordRepository;
        this.reservationRecordRepository = reservationRecordRepository;
        checkOutRecords = checkOutRecordRepository.findAll();
        reservationRecords = reservationRecordRepository.findAll();
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

    public void createReservationRecord(String bookId, String userId) {
        ReservationRecord reservationRecord = new ReservationRecord(bookId, userId, Instant.now());
        reservationRecords.add(reservationRecord);
        reservationRecordRepository.save(reservationRecord);
    }

    public List<CheckOutRecord> getCheckOutRecordList() {
        return checkOutRecords;
    }

    private List<CheckOutRecord> getCheckOutRecord(String bookId, String userId) {
        return checkOutRecords.stream().filter(x -> x.getBookId().equals(bookId) && x.getUserId().equals(userId)).toList();
    }

    public List<ReservationRecord> getReservationRecordList() {
        return reservationRecords;
    }

    public Optional<String> getReserver(String bookId) {
        List<ReservationRecord> reservationRecordList = reservationRecords.stream().filter(x -> x.getBookId().equals(bookId))
                .sorted(Comparator.comparing(ReservationRecord::getTimestamp))
                .toList();
        if(reservationRecordList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(reservationRecordList.get(0).getUserId());
    }
}