package ntut.edu.tw.bookerfly.handler;

import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import ntut.edu.tw.bookerfly.entity.collection.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetAllBookInformationsController {
    private Collection collection;

    @Autowired
    public GetAllBookInformationsController(Collection collection) {
        this.collection = collection;
    }

    @GetMapping(path = "bookerfly/collection/book-infos", produces = "application/json")
    public ResponseEntity<Object> getAllBookInformations() {
        try {
            List<BookInformation> allBookInformations = collection.getAllBookInformations();
            return new ResponseEntity<>(allBookInformations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get all book, caused by " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
