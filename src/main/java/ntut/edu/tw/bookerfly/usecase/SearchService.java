package ntut.edu.tw.bookerfly.usecase;

import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import java.util.ArrayList;
import java.util.List;

public class SearchService {
    public SearchResult searchBook(SearchOption searchOption, String keyword, List<BookInformation> bookInformations) {
        List<BookInformation> results = new ArrayList<>();
        for(BookInformation bookInformation : bookInformations) {
            switch (searchOption) {
                case TITLE:
                    if(bookInformation.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                        results.add(bookInformation);
                    }
                    break;
                case AUTHOR:
                    if(bookInformation.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                        results.add(bookInformation);
                    }
                    break;
                case TYPE:
                    if(bookInformation.getType().toLowerCase().contains(keyword.toLowerCase())) {
                        results.add(bookInformation);
                    }
                    break;
                case ANY_MATCH:
                    if(bookInformation.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                            bookInformation.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                            bookInformation.getType().toLowerCase().contains(keyword.toLowerCase())) {
                        results.add(bookInformation);
                    }
                    break;
                default:
                    break;
            }
        }
        return new SearchResult(results);
    }
}