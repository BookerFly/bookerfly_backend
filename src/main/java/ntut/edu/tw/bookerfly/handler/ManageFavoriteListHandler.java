package ntut.edu.tw.bookerfly.handler;

import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import ntut.edu.tw.bookerfly.entity.collection.Collection;
import ntut.edu.tw.bookerfly.entity.user.Borrower;
import ntut.edu.tw.bookerfly.entity.user.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ManageFavoriteListHandler {
    private Organization organization;
    private Collection collection;

    @Autowired
    public ManageFavoriteListHandler(Organization organization, Collection collection) {
        this.organization = organization;
        this.collection = collection;
    }

    @PostMapping(path = "bookerfly/organization/borrowers/{userId}/favorite-book/add", produces = "application/json")
    public ResponseEntity<String> addFavoriteBook(@PathVariable("userId") String userId,
                                                  @RequestParam String bookInfoId) {
        try {
            BookInformation bookInformation = collection.getBookInformationById(bookInfoId).get();

            organization.addFavoriteBook(userId, bookInformation);

            return new ResponseEntity<>("Success to add book to favorite list.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add book to favorite list, caused by " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "bookerfly/organization/borrowers/{userId}/favorite-book/remove", produces = "application/json")
    public ResponseEntity<String> removeFavoriteBook(@PathVariable("userId") String userId,
                                                  @RequestParam String bookInfoId) {
        try {
            BookInformation bookInformation = collection.getBookInformationById(bookInfoId).get();

            organization.removeFavoriteBook(userId, bookInformation);

            return new ResponseEntity<>("Success to remove book from favorite list.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to remove book from favorite list, caused by " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
