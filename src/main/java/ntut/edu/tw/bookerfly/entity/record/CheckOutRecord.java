package ntut.edu.tw.bookerfly.entity.record;

import ntut.edu.tw.bookerfly.entity.collection.BookStatus;
import javax.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @Column(name = "borrow_timestamp")
    private Instant borrowTimestamp;

    @Column(name = "return_timestamp")
    private Instant returnTimestamp;

    public CheckOutRecord() {}

    public CheckOutRecord(String bookTitle, String bookId, String userId, BookStatus bookStatus, Instant borrowTimestamp) {
        this.bookTitle = bookTitle;
        this.bookId = bookId;
        this.userId = userId;
        this.bookStatus = bookStatus;
        this.borrowTimestamp = borrowTimestamp;
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

    public Instant getBorrowTimestamp() {
        return borrowTimestamp;
    }

    public void setBorrowTimestamp(Instant timestamp) {
        this.borrowTimestamp = timestamp;
    }

    public Instant getReturnTimestamp() {
        return returnTimestamp;
    }

    public void setReturnTimestamp(Instant returnTimestamp) {
        this.returnTimestamp = returnTimestamp;
    }

    public void updateBookStatus(BookStatus bookStatus, Instant returnTimestamp) {
        this.bookStatus = bookStatus;
        this.returnTimestamp = returnTimestamp;
    }
}