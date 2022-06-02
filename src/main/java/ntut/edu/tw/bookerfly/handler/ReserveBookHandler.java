package ntut.edu.tw.bookerfly.handler;

import ntut.edu.tw.bookerfly.entity.collection.Book;
import ntut.edu.tw.bookerfly.entity.collection.BookStatus;
import ntut.edu.tw.bookerfly.entity.record.RecordManager;
import ntut.edu.tw.bookerfly.entity.user.Borrower;
import ntut.edu.tw.bookerfly.respository.collection.BookRepository;
import ntut.edu.tw.bookerfly.respository.user.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReserveBookHandler {
    private BookRepository bookRepository;
    private RecordManager recordManager;
    private BorrowerRepository borrowerRepository;

    @Autowired
    public ReserveBookHandler(BookRepository bookRepository, RecordManager recordManager, BorrowerRepository borrowerRepository) {
        this.bookRepository = bookRepository;
        this.recordManager = recordManager;
        this.borrowerRepository = borrowerRepository;
    }

    @PostMapping(path = "bookerfly/collection/books/{bookId}/reserve", produces = "application/json")
    public ResponseEntity<String> reserveBook(@PathVariable("bookId") String bookId,
                                             @RequestParam("userId") String userId) {
        try {
            Borrower borrower = borrowerRepository.findById(userId).get();
            Book book = bookRepository.findById(bookId).get();
            if (!borrower.hasBorrowQualification()) {
                return new ResponseEntity<>("Cannot reserve book since without qualification.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(book.getBookStatus().equals(BookStatus.AVAILABLE) || book.getBookStatus().equals(BookStatus.MISSING)) {
                return new ResponseEntity<>("Cannot reserve book since book is available or missing.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            recordManager.createReservationRecord(bookId, userId);
            return new ResponseEntity<>("Success to reserve book.", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Failed to reserve book, caused by " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
