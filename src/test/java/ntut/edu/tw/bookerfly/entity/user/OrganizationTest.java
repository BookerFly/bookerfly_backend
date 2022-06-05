package ntut.edu.tw.bookerfly.entity.user;

import ntut.edu.tw.bookerfly.AbstractSpringJpaTest;
import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import ntut.edu.tw.bookerfly.entity.favoritelist.FavoriteList;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrganizationTest extends AbstractSpringJpaTest {

    @Test
    public void get_borrower() {
        Organization organization = new Organization(borrowerRepository);
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId, "Jay", "test@gmail.com");
        organization.addBorrower(borrower);

        Optional<Borrower> readBorrower = organization.getBorrower(borrower.getUserId());
        assertTrue(readBorrower.isPresent());
        assertEquals(borrower.getUserId(), readBorrower.get().getUserId());
    }

    @Test
    public void add_favorite_book_to_user_favorite_list() {
        Organization organization = new Organization(borrowerRepository);
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId, "Jay", "test@gmail.com");
        organization.addBorrower(borrower);
        BookInformation bookInformation = new BookInformation("端午節", "屈原", UUID.randomUUID().toString(), "image", "type");

        organization.addFavoriteBook(userId, bookInformation);

        Borrower readBorrower = organization.getBorrower(borrower.getUserId()).get();
        FavoriteList favoriteList = readBorrower.getFavoriteList();
        assertEquals(1, favoriteList.getBookInfoList().size());
        assertTrue(favoriteList.getBookInfoList().stream().anyMatch(x -> x.getBookInfoId().equals(bookInformation.getBookInfoId())));
    }

    @Test
    public void remove_favorite_book_to_user_favorite_list() {
        Organization organization = new Organization(borrowerRepository);
        String userId = UUID.randomUUID().toString();
        Borrower borrower = new Borrower(userId, "Jay", "test@gmail.com");
        organization.addBorrower(borrower);
        BookInformation bookInformation = new BookInformation("端午節", "屈原", UUID.randomUUID().toString(), "image", "type");
        organization.addFavoriteBook(userId, bookInformation);

        organization.removeFavoriteBook(userId, bookInformation);

        Borrower readBorrower = organization.getBorrower(borrower.getUserId()).get();
        assertTrue(readBorrower.getFavoriteList().isEmpty());
    }
}
