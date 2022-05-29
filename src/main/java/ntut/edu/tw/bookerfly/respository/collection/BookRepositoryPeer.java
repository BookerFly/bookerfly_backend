package ntut.edu.tw.bookerfly.respository.collection;

import ntut.edu.tw.bookerfly.entity.collection.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoryPeer extends CrudRepository<Book, String> {
}