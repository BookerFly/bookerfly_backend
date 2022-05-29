package ntut.edu.tw.bookerfly.respository.record;

import ntut.edu.tw.bookerfly.entity.record.ReservationRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRecordRepositoryPeer extends CrudRepository<ReservationRecord, String>{
}
