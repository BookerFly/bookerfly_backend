package ntut.edu.tw.bookerfly.usecase;

import ntut.edu.tw.bookerfly.AbstractSpringJpaTest;
import ntut.edu.tw.bookerfly.entity.collection.Book;
import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import ntut.edu.tw.bookerfly.entity.collection.BookStatus;
import ntut.edu.tw.bookerfly.entity.record.CheckOutRecord;
import ntut.edu.tw.bookerfly.entity.user.Borrower;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class BorrowBookTest extends AbstractSpringJpaTest {
    @Test
    public void borrow_a_book() {
        collection.createBook("title", "author", ISBN, "image", "type", "Lab1321", 1, 1);
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId);
        BookInformation bookInformation = collection.getAllBookInformations().stream().filter(x -> x.getISBN().equals(ISBN)).findFirst().get();
        List<Book> bookList = collection.selectBook(bookInformation.getBookInfoId());
        int originalCount = recordManager.getCheckOutRecordList().size();

        assertTrue(borrower.hasBorrowQualification());
        Book book = bookList.get(0);
        collection.borrowBook(book.getBookId());
        recordManager.createCheckOutRecord(bookInformation.getTitle(), book.getBookId(), userId);
        borrower.increaseLoanItemCount();

        int currentCount = recordManager.getCheckOutRecordList().size();
        assertEquals(BookStatus.CHECKED_OUT, book.getBookStatus());
        List<CheckOutRecord> checkOutRecordList = recordManager.getCheckOutRecordList();
        assertEquals(1, currentCount - originalCount);
        CheckOutRecord checkOutRecord = checkOutRecordList.get(currentCount - 1);
        assertEquals(book.getBookId(), checkOutRecord.getBookId());
        assertEquals(BookStatus.CHECKED_OUT, checkOutRecord.getBookStatus());
        assertEquals(userId, checkOutRecord.getUserId());
        assertTrue(borrower.hasBorrowQualification());
    }

    @Test
    public void cannot_borrow_book() {
        collection.createBook("title", "author", ISBN, "image", "type", "Lab1321", 1, 3);
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId);
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

        assertFalse(borrower.hasBorrowQualification());
    }

    @Test
    public void borrow_a_checked_out_book() {
        collection.createBook("title", "author", ISBN, "image", "type", "Lab1321", 1, 1);
        String userId = UUID.randomUUID().toString();
        BookInformation bookInformation = collection.getAllBookInformations().stream().filter(x -> x.getISBN().equals(ISBN)).findFirst().get();
        List<Book> bookList = collection.selectBook(bookInformation.getBookInfoId());
        Book book = bookList.get(0);
        collection.borrowBook(book.getBookId());
        recordManager.createCheckOutRecord(bookInformation.getTitle(), book.getBookId(), userId);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            collection.borrowBook(book.getBookId());
        });

        assertEquals("The book is already checked out.", exception.getMessage());
    }

    @Test
    public void select_books() {
        collection.createBook("title", "author", ISBN, "image", "type", "Lab1321", 1, 2);

        List<Book> books = collection.selectBook("title", "author", ISBN, "image", "type");

        assertEquals(2, books.size());
        assertTrue(books.get(0).hasSameBookInfo(books.get(1).getTitle(), books.get(1).getAuthor(), books.get(1).getISBN(), books.get(1).getImage(), books.get(1).getType()));
    }
}