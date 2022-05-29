package ntut.edu.tw.bookerfly.respository.record;

import ntut.edu.tw.bookerfly.entity.record.CheckOutRecord;
import java.util.ArrayList;
import java.util.List;

public class CheckOutRecordRepository {

    private CheckOutRecordRepositoryPeer peer;

    public CheckOutRecordRepository(CheckOutRecordRepositoryPeer peer) {
        this.peer = peer;
    }

    public void save(CheckOutRecord checkOutRecord) {
        peer.save(checkOutRecord);
    }

    public List<CheckOutRecord> findAll() {
        List<CheckOutRecord> result = new ArrayList<>();
        peer.findAll().forEach(result::add);
        return result;
    }
}
