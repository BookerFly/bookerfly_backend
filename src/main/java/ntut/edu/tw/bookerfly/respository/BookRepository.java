package ntut.edu.tw.bookerfly.respository;

import ntut.edu.tw.bookerfly.entity.collection.Book;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepository {

    private BookRepositoryPeer peer;

    @Autowired
    public BookRepository(BookRepositoryPeer peer) {
        this.peer = peer;
    }

    public Optional<Book> findById(String bookId) {
        return peer.findById(bookId);
    }

    public void save(Book book) {
        peer.save(book);
    }

    public List<Book> findAll() {
        List<Book> result = new ArrayList<>();
        peer.findAll().forEach(result::add);
        return result;
    }
}