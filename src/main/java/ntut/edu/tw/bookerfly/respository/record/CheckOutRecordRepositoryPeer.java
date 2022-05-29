package ntut.edu.tw.bookerfly.respository.record;

import ntut.edu.tw.bookerfly.entity.record.CheckOutRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckOutRecordRepositoryPeer extends CrudRepository<CheckOutRecord, String> {
}
