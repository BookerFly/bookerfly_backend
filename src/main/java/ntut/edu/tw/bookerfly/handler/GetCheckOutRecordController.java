package ntut.edu.tw.bookerfly.handler;

import ntut.edu.tw.bookerfly.entity.collection.BookStatus;
import ntut.edu.tw.bookerfly.entity.record.CheckOutRecord;
import ntut.edu.tw.bookerfly.entity.record.RecordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class GetCheckOutRecordController {
    private RecordManager recordManager;

    @Autowired
    public GetCheckOutRecordController(RecordManager recordManager) {
        this.recordManager = recordManager;
    }

    @GetMapping(path = "bookerfly/record/check-out-record/all", produces = "application/json")
    public ResponseEntity<Object> getAllCheckOutRecord() {
        try {
            return new ResponseEntity<>(recordManager.getCheckOutRecordList(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get all check out records, caused by " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "bookerfly/record/check-out-record", produces = "application/json")
    public ResponseEntity<Object> getCheckOutRecordByUserId(@RequestParam("userId") String userId) {
        try {
            List<CheckOutRecord> checkOutRecordList = recordManager.getCheckOutRecordList().stream().filter(x -> x.getUserId().equals(userId)).toList();
            return new ResponseEntity<>(checkOutRecordList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get the user's check out records, caused by " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "bookerfly/record/check-out-record/processing", produces = "application/json")
    public ResponseEntity<Object> getProcessingCheckoutRecord() {
        try {
            List<CheckOutRecord> checkOutRecordList = recordManager.getCheckOutRecordList().stream().filter(x -> x.getBookStatus().equals(BookStatus.PROCESSING)).toList();
            return new ResponseEntity<>(checkOutRecordList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get processing check out records, caused by " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
