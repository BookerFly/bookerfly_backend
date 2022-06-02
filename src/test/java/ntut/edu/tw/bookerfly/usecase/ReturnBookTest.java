package ntut.edu.tw.bookerfly.usecase;

import ntut.edu.tw.bookerfly.AbstractSpringJpaTest;
import ntut.edu.tw.bookerfly.entity.collection.Book;
import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import ntut.edu.tw.bookerfly.entity.collection.BookStatus;
import ntut.edu.tw.bookerfly.entity.record.CheckOutRecord;
import ntut.edu.tw.bookerfly.entity.user.Borrower;
import ntut.edu.tw.bookerfly.service.NoticeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

public class ReturnBookTest extends AbstractSpringJpaTest {
    @Test
    public void return_a_book() {
        collection.createBook("title", "author", ISBN, "image", "type", "Lab1321", 1, 1);
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId, "Jay", "test@gmail.com");
        BookInformation bookInformation = collection.getAllBookInformations().stream().filter(x -> x.getISBN().equals(ISBN)).findFirst().get();
        List<Book> bookList = collection.selectBook(bookInformation.getBookInfoId());
        assertTrue(borrower.hasBorrowQualification());
        Book book = bookList.get(0);
        collection.borrowBook(book.getBookId());
        recordManager.createCheckOutRecord(bookInformation.getTitle(), book.getBookId(), userId);
        borrower.increaseLoanItemCount();

        collection.returnBook(book.getBookId());
        recordManager.updateCheckOutRecord(book.getBookId(), userId, BookStatus.PROCESSING);
        collection.confirmBookReturned(book.getBookId());
        recordManager.updateCheckOutRecord(book.getBookId(), userId, BookStatus.AVAILABLE);
        borrower.decreaseLoanItemCount();

        assertEquals(BookStatus.AVAILABLE, book.getBookStatus());
        List<CheckOutRecord> checkOutRecordList = recordManager.getCheckOutRecordList();
        CheckOutRecord checkOutRecord = checkOutRecordList.get(checkOutRecordList.size() - 1);
        assertEquals(book.getBookId(), checkOutRecord.getBookId());
        assertEquals(BookStatus.AVAILABLE, checkOutRecord.getBookStatus());
        assertEquals(userId, checkOutRecord.getUserId());
        assertTrue(borrower.hasBorrowQualification());
    }

    @Test
    public void can_not_borrow_book_before_manager_confirms() {
        collection.createBook("title", "author", ISBN, "image", "type", "Lab1321", 1, 3);
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId, "Jay", "test@gmail.com");
        List<Book> bookList = collection.selectBook("title", "author", ISBN, "image", "type");
        collection.borrowBook(bookList.get(0).getBookId());
        recordManager.createCheckOutRecord("title", bookList.get(0).getBookId(), userId);
        borrower.increaseLoanItemCount();
        collection.borrowBook(bookList.get(1).getBookId());
        recordManager.createCheckOutRecord("title", bookList.get(1).getBookId(), userId);
        borrower.increaseLoanItemCount();
        collection.borrowBook(bookList.get(2).getBookId());
        recordManager.createCheckOutRecord("title", bookList.get(2).getBookId(), userId);
        borrower.increaseLoanItemCount();

        collection.returnBook(bookList.get(2).getBookId());
        recordManager.updateCheckOutRecord(bookList.get(2).getBookId(), userId, BookStatus.PROCESSING);

        assertFalse(borrower.hasBorrowQualification());
    }

    @Test
    public void can_borrow_book_after_manager_confirms() {
        collection.createBook("title", "author", ISBN, "image", "type", "Lab1321", 1, 3);
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId, "Jay", "test@gmail.com");
        List<Book> bookList = collection.selectBook("title", "author", ISBN, "image", "type");
        collection.borrowBook(bookList.get(0).getBookId());
        recordManager.createCheckOutRecord("title", bookList.get(0).getBookId(), userId);
        borrower.increaseLoanItemCount();
        collection.borrowBook(bookList.get(1).getBookId());
        recordManager.createCheckOutRecord("title", bookList.get(1).getBookId(), userId);
        borrower.increaseLoanItemCount();
        collection.borrowBook(bookList.get(2).getBookId());
        recordManager.createCheckOutRecord("title", bookList.get(2).getBookId(), userId);
        borrower.increaseLoanItemCount();

        collection.returnBook(bookList.get(2).getBookId());
        recordManager.updateCheckOutRecord(bookList.get(2).getBookId(), userId, BookStatus.PROCESSING);
        collection.confirmBookReturned(bookList.get(2).getBookId());
        recordManager.updateCheckOutRecord(bookList.get(2).getBookId(), userId, BookStatus.AVAILABLE);
        borrower.decreaseLoanItemCount();

        Optional<String> reserverId = recordManager.getReserver(bookList.get(2).getBookId());

        assertTrue(reserverId.isEmpty());
        assertTrue(borrower.hasBorrowQualification());
    }

    @Test
    public void lost_a_book() {
        collection.createBook("title", "author", ISBN, "image", "type", "Lab1321", 1, 3);
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId, "Jay", "test@gmail.com");
        List<Book> bookList = collection.selectBook("title", "author", ISBN, "image", "type");
        collection.borrowBook(bookList.get(0).getBookId());
        recordManager.createCheckOutRecord("title", bookList.get(0).getBookId(), userId);
        borrower.increaseLoanItemCount();
        collection.borrowBook(bookList.get(1).getBookId());
        recordManager.createCheckOutRecord("title", bookList.get(1).getBookId(), userId);
        borrower.increaseLoanItemCount();
        collection.borrowBook(bookList.get(2).getBookId());
        recordManager.createCheckOutRecord("title", bookList.get(2).getBookId(), userId);
        borrower.increaseLoanItemCount();

        collection.returnBook(bookList.get(2).getBookId());
        recordManager.updateCheckOutRecord(bookList.get(2).getBookId(), userId, BookStatus.PROCESSING);
        collection.lostBook(bookList.get(2).getBookId());
        recordManager.updateCheckOutRecord(bookList.get(2).getBookId(), userId, BookStatus.MISSING);

        assertFalse(borrower.hasBorrowQualification());
        assertEquals(BookStatus.MISSING, bookList.get(2).getBookStatus());
        List<CheckOutRecord> checkOutRecordList = recordManager.getCheckOutRecordList();
        CheckOutRecord checkOutRecord = checkOutRecordList.get(checkOutRecordList.size() - 1);
        assertEquals(BookStatus.MISSING, checkOutRecord.getBookStatus());
    }

    @Test
    public void notify_reserver_after_confirming_book_return() {
        collection.createBook("title", "author", ISBN, "image", "type", "Lab1321", 1, 1);
        String userId1 = UUID.randomUUID().toString();
        String userId2 = UUID.randomUUID().toString();
        Borrower borrower1 = new Borrower(userId1, "Jay", "firstmail@gmail.com");
        Borrower borrower2 = new Borrower(userId2, "Jay", "secondmail@gmail.com");
        List<Book> bookList = collection.selectBook("title", "author", ISBN, "image", "type");
        String bookId = bookList.get(0).getBookId();
        collection.borrowBook(bookId);
        recordManager.createCheckOutRecord("title", bookId, userId1);
        borrower1.increaseLoanItemCount();
        recordManager.createReservationRecord(bookId, userId2);

        collection.returnBook(bookList.get(0).getBookId());
        recordManager.updateCheckOutRecord(bookList.get(0).getBookId(), userId1, BookStatus.PROCESSING);
        collection.confirmBookReturned(bookList.get(0).getBookId());
        recordManager.updateCheckOutRecord(bookList.get(0).getBookId(), userId1, BookStatus.AVAILABLE);
        borrower1.decreaseLoanItemCount();

        Optional<String> reserverId = recordManager.getReserver(bookId);
        collection.withholdBook(bookId);
        NoticeService noticeService = Mockito.mock(NoticeService.class);
        noticeService.notice(bookId, reserverId.get());

        assertTrue(reserverId.isPresent());
        assertEquals(userId2, reserverId.get());
        await().untilAsserted(()-> Mockito.verify(noticeService).notice(eq(bookId), eq(userId2)));
        assertEquals(BookStatus.RESERVED, bookList.get(0).getBookStatus());
    }

}
