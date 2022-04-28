package ntut.edu.tw.bookerfly.entity.record;

import ntut.edu.tw.bookerfly.entity.collection.BookStatus;
import java.time.Instant;

public class CheckOutRecord {
    private String bookId;
    private String userId;
    private BookStatus bookStatus;
    private Instant timestamp;

    public CheckOutRecord(String bookId, String userId, BookStatus bookStatus, Instant timestamp) {
        this.bookId = bookId;
        this.userId = userId;
        this.bookStatus = bookStatus;
        this.timestamp = timestamp;
    }

    public String getBookId() {
        return bookId;
    }

    public String getUserId() {
        return userId;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}