package ntut.edu.tw.bookerfly.usecase;

import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import java.util.List;

public class SearchResult {
    List<BookInformation> results;

    public SearchResult(List<BookInformation> results) {
        this.results = results;
    }

    public List<BookInformation> getResults() {
        return results;
    }
}