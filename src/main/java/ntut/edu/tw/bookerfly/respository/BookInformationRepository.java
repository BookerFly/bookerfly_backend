package ntut.edu.tw.bookerfly.respository;

import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookInformationRepository {

    private BookInformationRepositoryPeer peer;

    @Autowired
    public BookInformationRepository(BookInformationRepositoryPeer peer) {
        this.peer = peer;
    }

    public Optional<BookInformation> findById(String bookInfoId) {
        return peer.findById(bookInfoId);
    }

    public void save(BookInformation bookInformation) {
        peer.save(bookInformation);
    }

    public List<BookInformation> findAll() {
        List<BookInformation> result = new ArrayList<>();
        peer.findAll().forEach(result::add);
        return result;
    }
}