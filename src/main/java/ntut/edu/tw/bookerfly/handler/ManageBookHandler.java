package ntut.edu.tw.bookerfly.handler;

import ntut.edu.tw.bookerfly.entity.collection.Collection;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManageBookHandler {

    private Collection collection;

    @Autowired
    public ManageBookHandler(Collection collection) {
        this.collection = collection;
    }

    @PostMapping(path = "bookerfly/collection/books/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> createBook(@RequestBody String bookPayload) {
        try {
            JSONObject bookJson = new JSONObject(bookPayload);
            String title = bookJson.getString("title");
            String author = bookJson.getString("author");
            String ISBN = bookJson.getString("ISBN");
            String image = bookJson.getString("image");
            String type = bookJson.getString("type");
            String bookshelfPosition = bookJson.getString("bookshelfPosition");
            int bookshelfNumber = bookJson.getInt("bookshelfNumber");
            int count = bookJson.getInt("count");

            collection.createBook(title, author, ISBN, image, type, bookshelfPosition, bookshelfNumber, count);

            return new ResponseEntity<>("Success to create book.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create book, caused by " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
