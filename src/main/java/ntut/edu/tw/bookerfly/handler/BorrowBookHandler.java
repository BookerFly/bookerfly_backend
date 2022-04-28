package ntut.edu.tw.bookerfly.handler;

import ntut.edu.tw.bookerfly.entity.collection.Book;
import ntut.edu.tw.bookerfly.entity.collection.Collection;
import ntut.edu.tw.bookerfly.entity.record.RecordManager;
import ntut.edu.tw.bookerfly.entity.user.Borrower;
import ntut.edu.tw.bookerfly.respository.BorrowerRepository;
import java.util.List;

public class BorrowBookHandler {
    private Collection collection;
    private RecordManager recordManager;
    private BorrowerRepository borrowerRepository;

    public BorrowBookHandler(Collection collection, RecordManager recordManager, BorrowerRepository borrowerRepository) {
        this.collection = collection;
        this.recordManager = recordManager;
        this.borrowerRepository = borrowerRepository;
    }

    public List<Book> selectBook(String bookInfoId) {
        return collection.selectBook(bookInfoId);
    }

    public String borrowBook(String bookId, String userId) {
        Borrower borrower = borrowerRepository.findById(userId).get();
        if(borrower.hasBorrowQualification()) {
            collection.borrowBook(bookId);
            recordManager.createCheckOutRecord(bookId, userId);
            borrower.increaseLoanItemCount();
            return "Success to borrow book.";
        }
        return "Failed to borrow book.";
    }
}