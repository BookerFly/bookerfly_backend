package ntut.edu.tw.bookerfly.handler;

import ntut.edu.tw.bookerfly.entity.collection.Book;
import ntut.edu.tw.bookerfly.entity.collection.Collection;
import ntut.edu.tw.bookerfly.entity.record.RecordManager;
import ntut.edu.tw.bookerfly.entity.user.Borrower;
import ntut.edu.tw.bookerfly.respository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BorrowBookHandler {
    private Collection collection;
    private RecordManager recordManager;
    private BorrowerRepository borrowerRepository;

    @Autowired
    public BorrowBookHandler(Collection collection, RecordManager recordManager, BorrowerRepository borrowerRepository) {
        this.collection = collection;
        this.recordManager = recordManager;
        this.borrowerRepository = borrowerRepository;
    }

    @GetMapping(path = "bookerfly/collection/select/book-infos/{bookInfoId}", produces = "application/json")
    public List<Book> selectBook(@PathVariable("bookInfoId") String bookInfoId) {
        return collection.selectBook(bookInfoId);
    }

    @PostMapping(path = "bookerfly/collection/books/{bookId}/borrow", produces = "application/json")
    public ResponseEntity<String> borrowBook(@PathVariable("bookId") String bookId,
                             @RequestParam("userId") String userId) {
        try {
            Borrower borrower = borrowerRepository.findById(userId).get();
            if (borrower.hasBorrowQualification()) {
                collection.borrowBook(bookId);
                recordManager.createCheckOutRecord(bookId, userId);
                borrower.increaseLoanItemCount();
                borrowerRepository.save(borrower);
                return new ResponseEntity<>("Success to borrow book.", HttpStatus.OK);
            }
            return new ResponseEntity<>("Cannot borrow book since without qualification.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to borrow book, caused by " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}