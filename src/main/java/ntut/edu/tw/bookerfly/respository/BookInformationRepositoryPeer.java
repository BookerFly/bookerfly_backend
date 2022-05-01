package ntut.edu.tw.bookerfly.respository;

import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInformationRepositoryPeer extends CrudRepository<BookInformation, String> {
}