package ntut.edu.tw.bookerfly.entity.record;

import ntut.edu.tw.bookerfly.entity.collection.BookStatus;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "check_out_record")
public class CheckOutRecord {
    @Id
    @Column(name = "check_out_record_id")
    private String checkOutRecordId;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "book_id")
    private String bookId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "book_status")
    private BookStatus bookStatus;

    @Column(name = "timestamp")
    private Instant timestamp;

    public CheckOutRecord() {}

    public CheckOutRecord(String bookTitle, String bookId, String userId, BookStatus bookStatus, Instant timestamp) {
        this.bookTitle = bookTitle;
        this.bookId = bookId;
        this.userId = userId;
        this.bookStatus = bookStatus;
        this.timestamp = timestamp;
        checkOutRecordId = UUID.randomUUID().toString();
    }

    public String getCheckOutRecordId() {
        return checkOutRecordId;
    }

    public void setCheckOutRecordId(String checkOutRecordId) {
        this.checkOutRecordId = checkOutRecordId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
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

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}