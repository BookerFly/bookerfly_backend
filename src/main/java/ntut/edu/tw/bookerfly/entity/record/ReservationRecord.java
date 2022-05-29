package ntut.edu.tw.bookerfly.entity.record;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "reservation_record")
public class ReservationRecord {
    @Id
    @Column(name = "reservation_record_id")
    private String reservationRecordId;

    @Column(name = "book_id")
    private String bookId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "timestamp")
    private Instant timestamp;

    public ReservationRecord() {}

    public ReservationRecord(String bookId, String userId, Instant timestamp) {
        this.bookId = bookId;
        this.userId = userId;
        this.timestamp = timestamp;
        reservationRecordId = UUID.randomUUID().toString();
    }

    public String getReservationRecordId() {
        return reservationRecordId;
    }

    public void setReservationRecordId(String reservationRecordId) {
        this.reservationRecordId = reservationRecordId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
