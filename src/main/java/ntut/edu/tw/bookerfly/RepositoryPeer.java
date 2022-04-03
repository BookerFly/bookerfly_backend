package ntut.edu.tw.bookerfly;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ntut.edu.tw.bookerfly.Book;
import java.util.UUID;

@Repository
public interface RepositoryPeer extends CrudRepository<Book, UUID> {
}