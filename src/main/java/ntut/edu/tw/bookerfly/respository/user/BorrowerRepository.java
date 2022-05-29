package ntut.edu.tw.bookerfly.respository.user;

import ntut.edu.tw.bookerfly.entity.user.Borrower;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

public class BorrowerRepository {

    private BorrowerRepositoryPeer peer;

    @Autowired
    public BorrowerRepository(BorrowerRepositoryPeer peer) {
        this.peer = peer;
    }

    public Optional<Borrower> findById(String userId) {
        return peer.findById(userId);
    }

    public void save(Borrower borrower) {
        peer.save(borrower);
    }
}