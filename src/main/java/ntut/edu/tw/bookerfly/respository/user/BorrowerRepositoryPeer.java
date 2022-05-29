package ntut.edu.tw.bookerfly.respository.user;

import ntut.edu.tw.bookerfly.entity.user.Borrower;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepositoryPeer extends CrudRepository<Borrower, String> {
}