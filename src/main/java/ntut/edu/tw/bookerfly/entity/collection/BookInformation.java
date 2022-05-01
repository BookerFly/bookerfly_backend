package ntut.edu.tw.bookerfly.entity.collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "book_information")
public class BookInformation {
    @Id
    @Column(name = "book_info_id")
    private String bookInfoId;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "ISBN")
    private String ISBN;

    @Column(name = "image")
    private String image;

    @Column(name = "type")
    private String type;

    public BookInformation() {
    }

    public BookInformation(String title, String author, String ISBN, String image, String type) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.image = image;
        this.type = type;
        bookInfoId = UUID.randomUUID().toString();
    }

    public String getBookInfoId() {
        return bookInfoId;
    }

    public void setBookInfoId(String bookInfoId) {
        this.bookInfoId = bookInfoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}