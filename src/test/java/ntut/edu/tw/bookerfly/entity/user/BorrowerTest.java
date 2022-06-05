package ntut.edu.tw.bookerfly.entity.user;

import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class BorrowerTest {
    @Test
    public void borrower_has_borrow_qualification() {
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId, "Jay", "test@gmail.com");

        assertTrue(borrower.hasBorrowQualification());
    }

    @Test
    public void borrower_has_borrow_qualification_after_borrowing_a_book() {
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId, "Jay", "test@gmail.com");
        borrower.increaseLoanItemCount();

        assertTrue(borrower.hasBorrowQualification());
    }

    @Test
    public void borrower_has_no_borrow_qualification_after_borrowing_three_books() {
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId, "Jay", "test@gmail.com");
        borrower.increaseLoanItemCount();
        borrower.increaseLoanItemCount();
        borrower.increaseLoanItemCount();

        assertFalse(borrower.hasBorrowQualification());
    }

    @Test
    public void borrower_has_borrow_qualification_after_return_a_book() {
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId, "Jay", "test@gmail.com");
        borrower.increaseLoanItemCount();
        borrower.increaseLoanItemCount();
        borrower.increaseLoanItemCount();

        borrower.decreaseLoanItemCount();

        assertTrue(borrower.hasBorrowQualification());
    }

    @Test
    public void borrower_has_empty_favorite_list_in_the_beginning() {
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId, "Jay", "test@gmail.com");

        assertTrue(borrower.getFavoriteList().isEmpty());
    }

    @Test
    public void borrower_adds_a_favorite_book() {
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId, "Jay", "test@gmail.com");
        BookInformation bookInformation = new BookInformation("端午節", "屈原", UUID.randomUUID().toString(), "image", "type");

        borrower.addFavoriteBook(bookInformation);

        assertFalse(borrower.getFavoriteList().isEmpty());
        assertTrue(borrower.getFavoriteList().getBookInfoList().contains(bookInformation));
    }

    @Test
    public void borrower_removes_a_favorite_book() {
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId, "Jay", "test@gmail.com");
        BookInformation bookInformation = new BookInformation("端午節", "屈原", UUID.randomUUID().toString(), "image", "type");
        borrower.addFavoriteBook(bookInformation);

        borrower.removeFavoriteBook(bookInformation);

        assertTrue(borrower.getFavoriteList().isEmpty());
    }

    @Test
    public void borrower_adds_existing_favorite_book_can_not_duplicate() {
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId, "Jay", "test@gmail.com");
        BookInformation bookInformation = new BookInformation("端午節", "屈原", UUID.randomUUID().toString(), "image", "type");
        borrower.addFavoriteBook(bookInformation);

        borrower.addFavoriteBook(bookInformation);

        assertFalse(borrower.getFavoriteList().isEmpty());
        assertEquals(1, borrower.getFavoriteList().getBookInfoList().size());
    }
}