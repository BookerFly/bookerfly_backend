package ntut.edu.tw.bookerfly.handler;

import ntut.edu.tw.bookerfly.entity.collection.BookStatus;
import ntut.edu.tw.bookerfly.entity.collection.Collection;
import ntut.edu.tw.bookerfly.entity.record.RecordManager;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ManageBookHandler {

    private Collection collection;
    private RecordManager recordManager;

    @Autowired
    public ManageBookHandler(Collection collection, RecordManager recordManager) {
        this.collection = collection;
        this.recordManager = recordManager;
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

    @PutMapping(path = "bookerfly/collection/books/{bookId}/edit", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> editBook(@PathVariable("bookId") String bookId,
                                            @RequestBody String bookPayload) {
        try {
            JSONObject bookJson = new JSONObject(bookPayload);
            String userId = bookJson.getString("userId");
            String bookshelfPosition = bookJson.getString("bookshelfPosition");
            int bookshelfNumber = bookJson.getInt("bookshelfNumber");
            String bookStatus = bookJson.getString("bookStatus");

            collection.editBook(bookId, bookshelfPosition, bookshelfNumber, BookStatus.valueOf(bookStatus));
            recordManager.updateCheckOutRecord(bookId, userId, BookStatus.valueOf(bookStatus));
            return new ResponseEntity<>("Success to edit book.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to edit book, caused by " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "bookerfly/collection/books/{bookId}/edit/book-status", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> editBookStatus(@PathVariable("bookId") String bookId,
                                           @RequestBody String bookPayload) {
        try {
            JSONObject bookJson = new JSONObject(bookPayload);
            String userId = bookJson.getString("userId");
            String bookStatus = bookJson.getString("bookStatus");

            collection.editBook(bookId, BookStatus.valueOf(bookStatus));
            recordManager.updateCheckOutRecord(bookId, userId, BookStatus.valueOf(bookStatus));
            return new ResponseEntity<>("Success to edit book.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to edit book, caused by " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
