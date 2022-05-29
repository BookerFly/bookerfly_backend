package ntut.edu.tw.bookerfly.handler;

import ntut.edu.tw.bookerfly.entity.collection.BookStatus;
import ntut.edu.tw.bookerfly.entity.collection.Collection;
import ntut.edu.tw.bookerfly.entity.record.RecordManager;
import ntut.edu.tw.bookerfly.entity.user.Borrower;
import ntut.edu.tw.bookerfly.respository.user.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReturnBookHandler {
    private Collection collection;
    private RecordManager recordManager;
    private BorrowerRepository borrowerRepository;

    @Autowired
    public ReturnBookHandler(Collection collection, RecordManager recordManager, BorrowerRepository borrowerRepository) {
        this.collection = collection;
        this.recordManager = recordManager;
        this.borrowerRepository = borrowerRepository;
    }

    @PutMapping(path = "bookerfly/collection/books/{bookId}/return", produces = "application/json")
    public ResponseEntity<String> returnBook(@PathVariable("bookId") String bookId,
                                                     @RequestParam("userId") String userId) {
        try {
            collection.returnBook(bookId);
            recordManager.updateCheckOutRecord(bookId, userId, BookStatus.PROCESSING);

            return new ResponseEntity<>("Waiting for manager confirm.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to return book, caused by " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "bookerfly/collection/books/{bookId}/confirm-return", produces = "application/json")
    public ResponseEntity<String> confirmBookReturned(@PathVariable("bookId") String bookId,
                                             @RequestParam("userId") String userId) {
        try {
            collection.confirmBookReturned(bookId);
            recordManager.updateCheckOutRecord(bookId, userId, BookStatus.AVAILABLE);
            Borrower borrower = borrowerRepository.findById(userId).get();
            borrower.decreaseLoanItemCount();
            borrowerRepository.save(borrower);

            return new ResponseEntity<>("Success to confirm book returned.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to confirm book returned, caused by " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "bookerfly/collection/books/{bookId}/lost", produces = "application/json")
    public ResponseEntity<String> lostBook(@PathVariable("bookId") String bookId,
                                             @RequestParam("userId") String userId) {
        try {
            collection.lostBook(bookId);
            recordManager.updateCheckOutRecord(bookId, userId, BookStatus.MISSING);

            return new ResponseEntity<>("Success to report the lost of book.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to report the lost of book, caused by " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
