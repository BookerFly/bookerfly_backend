package ntut.edu.tw.bookerfly.entity.collection;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id")
    private String bookId;

    @ManyToOne
    @JoinTable(name = "book_info",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_info_id")})
    private BookInformation bookInformation;

    @Column(name = "book_status")
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @Column(name = "bookshelf_position")
    private String bookshelfPosition;

    @Column(name = "bookshelf_number")
    private int bookshelfNumber;

    public Book() {
    }

    public Book(BookInformation bookInformation, BookStatus bookStatus, String bookshelfPosition, int bookshelfNumber) {
        this.bookInformation = bookInformation;
        this.bookStatus = bookStatus;
        this.bookshelfPosition = bookshelfPosition;
        this.bookshelfNumber = bookshelfNumber;
        bookId = UUID.randomUUID().toString();
    }

    public boolean hasSameBookInfo(String bookInfoId) {
        return bookInformation.getBookInfoId().equals(bookInfoId);
    }

    public boolean hasSameBookInfo(String title, String author, String isbn, String image, String type) {
        return bookInformation.getTitle().equals(title) &&
                bookInformation.getAuthor().equals(author) &&
                bookInformation.getISBN().equals(isbn) &&
                bookInformation.getImage().equals(image) &&
                bookInformation.getType().equals(type);
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return bookInformation.getTitle();
    }

    public String getAuthor() {
        return bookInformation.getAuthor();
    }

    public String getISBN() {
        return bookInformation.getISBN();
    }

    public String getImage() {
        return bookInformation.getImage();
    }

    public String getType() {
        return bookInformation.getType();
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