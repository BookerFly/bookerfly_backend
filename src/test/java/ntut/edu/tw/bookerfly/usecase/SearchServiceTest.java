package ntut.edu.tw.bookerfly.usecase;

import ntut.edu.tw.bookerfly.AbstractSpringJpaTest;
import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchServiceTest extends AbstractSpringJpaTest {
    @Test
    public void use_a_keyword_and_option_to_search_but_nothing() {
        List<BookInformation> bookInformations = new ArrayList<>();
        bookInformations.add(new BookInformation("we love OOAD", "wk", "ISBN", "image", "Book"));
        bookInformations.add(new BookInformation("OOAD also love us", "wk", "ISBN", "image", "Book"));
        bookInformations.add(new BookInformation("DDD", "wk", "ISBN", "image", "Book"));
        SearchService searchService = new SearchService();

        SearchResult searchResult = searchService.searchBook(SearchOption.TITLE, "CA", bookInformations);

        assertEquals(0, searchResult.getResults().size());
    }

    @Test
    public void use_a_keyword_and_title_to_search() {
        List<BookInformation> bookInformations = new ArrayList<>();
        bookInformations.add(new BookInformation("we love OOAD", "wk", "ISBN", "image", "Book"));
        bookInformations.add(new BookInformation("OOAD also love us", "wk", "ISBN", "image", "Book"));
        bookInformations.add(new BookInformation("DDD", "wk", "ISBN", "image", "Book"));
        SearchService searchService = new SearchService();

        SearchResult searchResult = searchService.searchBook(SearchOption.TITLE, "OOAD", bookInformations);

        List<BookInformation> results = searchResult.getResults();
        assertEquals(2, results.size());
        assertTrue(results.get(0).getTitle().toLowerCase().contains("OOAD".toLowerCase()));
        assertTrue(results.get(1).getTitle().toLowerCase().contains("OOAD".toLowerCase()));
    }

    @Test
    public void use_a_keyword_and_author_to_search() {
        List<BookInformation> bookInformations = new ArrayList<>();
        bookInformations.add(new BookInformation("we love OOAD", "wkChen", "ISBN", "image", "Book"));
        bookInformations.add(new BookInformation("OOAD also love us", "wk", "ISBN", "image", "Book"));
        bookInformations.add(new BookInformation("DDD", "teddy", "ISBN", "image", "Book"));
        SearchService searchService = new SearchService();

        SearchResult searchResult = searchService.searchBook(SearchOption.AUTHOR, "wk", bookInformations);

        List<BookInformation> results = searchResult.getResults();
        assertEquals(2, results.size());
        assertTrue(results.get(0).getAuthor().toLowerCase().contains("wk".toLowerCase()));
        assertTrue(results.get(1).getAuthor().toLowerCase().contains("wk".toLowerCase()));
    }

    @Test
    public void use_a_keyword_and_type_to_search() {
        List<BookInformation> bookInformations = new ArrayList<>();
        bookInformations.add(new BookInformation("we love OOAD", "wkChen", "ISBN", "image", "Paper"));
        bookInformations.add(new BookInformation("OOAD also love us", "wk", "ISBN", "image", "Paper"));
        bookInformations.add(new BookInformation("DDD", "teddy", "ISBN", "image", "Book"));
        SearchService searchService = new SearchService();

        SearchResult searchResult = searchService.searchBook(SearchOption.TYPE, "Paper", bookInformations);

        List<BookInformation> results = searchResult.getResults();
        assertEquals(2, results.size());
        assertTrue(results.get(0).getType().toLowerCase().contains("Paper".toLowerCase()));
        assertTrue(results.get(1).getType().toLowerCase().contains("Paper".toLowerCase()));
    }

    @Test
    public void use_a_keyword_and_any_match_to_search() {
        List<BookInformation> bookInformations = new ArrayList<>();
        bookInformations.add(new BookInformation("We love OOAD", "booky", "ISBN", "image", "Paper"));
        bookInformations.add(new BookInformation("A book about OOAD", "wk", "ISBN", "image", "Paper"));
        bookInformations.add(new BookInformation("DDD", "teddy", "ISBN", "image", "Book"));
        SearchService searchService = new SearchService();

        SearchResult searchResult = searchService.searchBook(SearchOption.ANY_MATCH, "book", bookInformations);

        List<BookInformation> results = searchResult.getResults();
        assertEquals(3, results.size());
        assertTrue(results.get(0).getAuthor().toLowerCase().contains("book".toLowerCase()));
        assertTrue(results.get(1).getTitle().toLowerCase().contains("book".toLowerCase()));
        assertTrue(results.get(2).getType().toLowerCase().contains("book".toLowerCase()));
    }
}