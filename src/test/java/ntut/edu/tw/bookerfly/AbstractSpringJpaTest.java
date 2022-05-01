package ntut.edu.tw.bookerfly;

import ntut.edu.tw.bookerfly.config.RepositoryInjection;
import ntut.edu.tw.bookerfly.entity.collection.Collection;
import ntut.edu.tw.bookerfly.respository.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.UUID;

@AutoConfigureAfter({RepositoryInjection.class})
@SpringBootTest
public class AbstractSpringJpaTest {
    public Collection collection;
    public String ISBN;

    @Autowired
    private BookInformationRepositoryPeer bookInformationRepositoryPeer;

    @Autowired
    private BookRepositoryPeer bookRepositoryPeer;

    @Autowired
    private BorrowerRepositoryPeer borrowerRepositoryPeer;

    private BookInformationRepository bookInformationRepository;
    private BookRepository bookRepository;
    private BorrowerRepository borrowerRepository;

    @BeforeEach
    void setUp() {
        bookInformationRepository = new BookInformationRepository(bookInformationRepositoryPeer);
        bookRepository = new BookRepository(bookRepositoryPeer);
        borrowerRepository = new BorrowerRepository(borrowerRepositoryPeer);

        ISBN = UUID.randomUUID().toString();
        collection = new Collection(bookInformationRepository, bookRepository);
    }
}
