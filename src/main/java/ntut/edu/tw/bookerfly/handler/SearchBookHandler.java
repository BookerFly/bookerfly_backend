package ntut.edu.tw.bookerfly.handler;

import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import ntut.edu.tw.bookerfly.entity.collection.Collection;
import ntut.edu.tw.bookerfly.usecase.SearchOption;
import ntut.edu.tw.bookerfly.usecase.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class SearchBookHandler {
    private Collection collection;
    private SearchService searchService;

    @Autowired
    public SearchBookHandler(Collection collection, SearchService searchService) {
        this.collection = collection;
        this.searchService = searchService;
    }

    @GetMapping(path = "bookerfly/search", produces = "application/json")
    public ResponseEntity<Object> searchBook(@RequestParam("searchOption") String searchOption,
                                             @RequestParam("keyword") String keyword) {
        List<BookInformation> bookInformations = collection.getAllBookInformations();

        try {
            return new ResponseEntity<>(searchService.searchBook(SearchOption.valueOf(searchOption), keyword, bookInformations), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to search book, caused by " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}