package ntut.edu.tw.bookerfly.entity;

import java.util.UUID;

public class BookInformation {
    private String bookInfoId;
    private String title;
    private String author;
    private String ISBN;
    private String image;
    private String type;

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

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }
}