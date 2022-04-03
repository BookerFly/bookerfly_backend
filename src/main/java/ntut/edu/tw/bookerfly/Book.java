package ntut.edu.tw.bookerfly;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id")
    private UUID id;
    @Column(name = "title")
    private String title;

    public Book() {}

    public Book(String title) {
        this.title = title;
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}