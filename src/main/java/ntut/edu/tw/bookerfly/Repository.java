package ntut.edu.tw.bookerfly;

import ntut.edu.tw.bookerfly.Book;
import java.util.Optional;
import java.util.UUID;

public class Repository {
    private RepositoryPeer peer;
    public Repository(RepositoryPeer peer){
        this.peer = peer;
    }

    public void save(Book book) {
        peer.save(book);
    }

    public Optional<Book> findById(UUID bookId) {
        return peer.findById(bookId);
    }
}