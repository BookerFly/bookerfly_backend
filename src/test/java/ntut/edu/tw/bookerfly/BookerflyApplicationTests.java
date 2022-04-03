package ntut.edu.tw.bookerfly;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ntut.edu.tw.bookerfly.Book;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookerflyApplicationTests {
	@Autowired
	private RepositoryPeer peer;
	@Test
	void contextLoads() {
		Repository repository = new Repository(peer);
		Book book = new Book("bookname");
		repository.save(book);
		assertEquals("bookname", repository.findById(book.getId()).get().getTitle());
	}
}
