package ntut.edu.tw.bookerfly.entity.user;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BorrowerTest {
    @Test
    public void borrower_has_borrow_qualification() {
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId);

        assertTrue(borrower.hasBorrowQualification());
    }

    @Test
    public void borrower_has_borrow_qualification_after_borrowing_a_book() {
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId);
        borrower.increaseLoanItemCount();

        assertTrue(borrower.hasBorrowQualification());
    }

    @Test
    public void borrower_has_no_borrow_qualification_after_borrowing_three_books() {
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId);
        borrower.increaseLoanItemCount();
        borrower.increaseLoanItemCount();
        borrower.increaseLoanItemCount();

        assertFalse(borrower.hasBorrowQualification());
    }
}