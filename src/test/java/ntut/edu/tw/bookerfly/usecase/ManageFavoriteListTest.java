package ntut.edu.tw.bookerfly.usecase;

import ntut.edu.tw.bookerfly.AbstractSpringJpaTest;
import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import ntut.edu.tw.bookerfly.entity.user.Borrower;
import ntut.edu.tw.bookerfly.entity.user.Organization;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class ManageFavoriteListTest extends AbstractSpringJpaTest {
    @Test
    public void add_favorite_book() {
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId, "Jay", "test@gmail.com");
        Organization organization = new Organization(borrowerRepository);
        organization.addBorrower(borrower);
        BookInformation bookInformation = new BookInformation("端午節", "屈原", UUID.randomUUID().toString(), "image", "type");

        Borrower readBorrower = organization.getBorrower(userId).get();
        readBorrower.addFavoriteBook(bookInformation);
        borrowerRepository.save(readBorrower);

        assertFalse(borrower.getFavoriteList().isEmpty());
        assertTrue(borrower.getFavoriteList().getBookInfoList().contains(bookInformation));
        Borrower dbBorrower = borrowerRepository.findById(userId).get();
        assertFalse(dbBorrower.getFavoriteList().isEmpty());
        assertTrue(dbBorrower.getFavoriteList().getBookInfoList().stream().anyMatch(x -> x.getBookInfoId().equals(bookInformation.getBookInfoId())));
    }

    @Test
    public void remove_favorite_book() {
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId, "Jay", "test@gmail.com");
        Organization organization = new Organization(borrowerRepository);
        organization.addBorrower(borrower);
        BookInformation bookInformation = new BookInformation("端午節", "屈原", UUID.randomUUID().toString(), "image", "type");
        Borrower readBorrower = organization.getBorrower(userId).get();
        readBorrower.addFavoriteBook(bookInformation);
        borrowerRepository.save(readBorrower);

        readBorrower = organization.getBorrower(userId).get();
        readBorrower.removeFavoriteBook(bookInformation);
        borrowerRepository.save(readBorrower);

        assertTrue(borrower.getFavoriteList().isEmpty());
        Borrower dbBorrower = borrowerRepository.findById(userId).get();
        assertTrue(dbBorrower.getFavoriteList().isEmpty());
    }

    @Test
    public void add_existing_favorite_book() {
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId, "Jay", "test@gmail.com");
        Organization organization = new Organization(borrowerRepository);
        organization.addBorrower(borrower);
        BookInformation bookInformation = new BookInformation("端午節", "屈原", UUID.randomUUID().toString(), "image", "type");
        Borrower readBorrower = organization.getBorrower(userId).get();
        readBorrower.addFavoriteBook(bookInformation);
        borrowerRepository.save(readBorrower);

        BookInformation readBookInformation = borrowerRepository.findById(userId).get().getFavoriteList().getBookInfoList().iterator().next();
        readBorrower.addFavoriteBook(readBookInformation);

        assertFalse(readBorrower.getFavoriteList().isEmpty());
        assertEquals(1, readBorrower.getFavoriteList().getBookInfoList().size());
    }
}
