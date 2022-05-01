package ntut.edu.tw.bookerfly.entity.collection;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id")
    private String bookId;

    @Column(name = "book_info_id")
    private String bookInfoId;

    @Column(name = "book_status")
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @Column(name = "bookshelf_position")
    private String bookshelfPosition;

    @Column(name = "bookshelf_number")
    private int bookshelfNumber;

    public Book() {
    }

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

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookInfoId() {
        return bookInfoId;
    }

    public void setBookInfoId(String bookInfoId) {
        this.bookInfoId = bookInfoId;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getBookshelfPosition() {
        return bookshelfPosition;
    }

    public void setBookshelfPosition(String bookshelfPosition) {
        this.bookshelfPosition = bookshelfPosition;
    }

    public int getBookshelfNumber() {
        return bookshelfNumber;
    }

    public void setBookshelfNumber(int bookshelfNumber) {
        this.bookshelfNumber = bookshelfNumber;
    }
}