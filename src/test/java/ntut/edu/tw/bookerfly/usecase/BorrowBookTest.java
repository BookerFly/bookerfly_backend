package ntut.edu.tw.bookerfly.usecase;

import ntut.edu.tw.bookerfly.entity.collection.Book;
import ntut.edu.tw.bookerfly.entity.collection.BookStatus;
import ntut.edu.tw.bookerfly.entity.collection.Collection;
import ntut.edu.tw.bookerfly.entity.record.CheckOutRecord;
import ntut.edu.tw.bookerfly.entity.record.RecordManager;
import ntut.edu.tw.bookerfly.entity.user.Borrower;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class BorrowBookTest {
    @Test
    public void borrow_a_book() {
        Collection collection = new Collection();
        collection.createBook("title", "author", "isbn", "image", "type", "Lab1321", 1, 1);
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId);
        RecordManager recordManager = new RecordManager();
        String bookInfoId = collection.getAllBookInformations().get(0).getBookInfoId();
        List<Book> bookList = collection.selectBook(bookInfoId);

        assertTrue(borrower.hasBorrowQualification());
        Book book = bookList.get(0);
        collection.borrowBook(book.getBookId());
        recordManager.createCheckOutRecord(book.getBookId(), userId);
        borrower.increaseLoanItemCount();

        assertEquals(BookStatus.CHECKED_OUT, book.getBookStatus());
        List<CheckOutRecord> checkOutRecordList = recordManager.getCheckOutRecordList();
        assertEquals(1, checkOutRecordList.size());
        assertEquals(book.getBookId(), checkOutRecordList.get(0).getBookId());
        assertEquals(BookStatus.CHECKED_OUT, checkOutRecordList.get(0).getBookStatus());
        assertEquals(userId, checkOutRecordList.get(0).getUserId());
        assertTrue(borrower.hasBorrowQualification());
    }

    @Test
    public void cannot_borrow_book() {
        Collection collection = new Collection();
        collection.createBook("title", "author", "isbn", "image", "type", "Lab1321", 1, 1);
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId);
        borrower.increaseLoanItemCount();
        borrower.increaseLoanItemCount();
        borrower.increaseLoanItemCount();

        assertFalse(borrower.hasBorrowQualification());
    }

    @Test
    public void borrow_a_checked_out_book() {
        Collection collection = new Collection();
        collection.createBook("title", "author", "isbn", "image", "type", "Lab1321", 1, 1);
        String userId = UUID.randomUUID().toString();
        RecordManager recordManager = new RecordManager();
        String bookInfoId = collection.getAllBookInformations().get(0).getBookInfoId();
        List<Book> bookList = collection.selectBook(bookInfoId);
        Book book = bookList.get(0);
        collection.borrowBook(book.getBookId());
        recordManager.createCheckOutRecord(book.getBookId(), userId);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            collection.borrowBook(book.getBookId());
        });

        assertEquals("The book is already checked out.", exception.getMessage());
    }

    @Test
    public void select_books() {
        Collection collection = new Collection();
        collection.createBook("title", "author", "isbn", "image", "type", "Lab1321", 1, 2);
        String bookInfoId = collection.getAllBookInformations().get(0).getBookInfoId();

        List<Book> bookList = collection.selectBook(bookInfoId);

        assertEquals(2, bookList.size());
        assertEquals(bookInfoId, bookList.get(0).getBookInfoId());
        assertEquals(bookInfoId, bookList.get(1).getBookInfoId());
    }
}