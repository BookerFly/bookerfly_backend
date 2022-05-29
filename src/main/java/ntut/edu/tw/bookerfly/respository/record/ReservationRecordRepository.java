package ntut.edu.tw.bookerfly.respository.record;

import ntut.edu.tw.bookerfly.entity.record.ReservationRecord;
import java.util.ArrayList;
import java.util.List;

public class ReservationRecordRepository {

    private ReservationRecordRepositoryPeer peer;

    public ReservationRecordRepository(ReservationRecordRepositoryPeer peer) {
        this.peer = peer;
    }

    public void save(ReservationRecord reservationRecord) {
        peer.save(reservationRecord);
    }

    public List<ReservationRecord> findAll() {
        List<ReservationRecord> result = new ArrayList<>();
        peer.findAll().forEach(result::add);
        return result;
    }
}
