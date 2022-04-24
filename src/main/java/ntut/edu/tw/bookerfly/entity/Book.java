package ntut.edu.tw.bookerfly.entity;

import java.util.UUID;

public class Book {
    private String bookId;
    private String bookInfoId;
    private BookStatus bookStatus;
    private String bookshelfPosition;
    private int bookshelfNumber;

    public Book(String bookInfoId, BookStatus bookStatus, String bookshelfPosition, int bookshelfNumber) {
        this.bookInfoId = bookInfoId;
        this.bookStatus = bookStatus;
        this.bookshelfPosition = bookshelfPosition;
        this.bookshelfNumber = bookshelfNumber;
        bookId = UUID.randomUUID().toString();
    }

    public boolean hasSameBookInfo(String bookInfoId) {
        return this.bookInfoId.equals(bookInfoId);
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public void setBookshelfPosition(String bookshelfPosition) {
        this.bookshelfPosition = bookshelfPosition;
    }

    public void setBookshelfNumber(int bookshelfNumber) {
        this.bookshelfNumber = bookshelfNumber;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookInfoId() {
        return bookInfoId;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public String getBookshelfPosition() {
        return bookshelfPosition;
    }

    public int getBookshelfNumber() {
        return bookshelfNumber;
    }
}