package ntut.edu.tw.bookerfly;

import ntut.edu.tw.bookerfly.config.RepositoryInjection;
import ntut.edu.tw.bookerfly.entity.collection.Collection;
import ntut.edu.tw.bookerfly.entity.record.RecordManager;
import ntut.edu.tw.bookerfly.respository.collection.BookInformationRepository;
import ntut.edu.tw.bookerfly.respository.collection.BookInformationRepositoryPeer;
import ntut.edu.tw.bookerfly.respository.collection.BookRepository;
import ntut.edu.tw.bookerfly.respository.collection.BookRepositoryPeer;
import ntut.edu.tw.bookerfly.respository.record.CheckOutRecordRepository;
import ntut.edu.tw.bookerfly.respository.record.CheckOutRecordRepositoryPeer;
import ntut.edu.tw.bookerfly.respository.record.ReservationRecordRepository;
import ntut.edu.tw.bookerfly.respository.record.ReservationRecordRepositoryPeer;
import ntut.edu.tw.bookerfly.respository.user.BorrowerRepository;
import ntut.edu.tw.bookerfly.respository.user.BorrowerRepositoryPeer;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.UUID;

@AutoConfigureAfter({RepositoryInjection.class})
@SpringBootTest
public class AbstractSpringJpaTest {
    public String ISBN;
    public Collection collection;
    public RecordManager recordManager;
    public BorrowerRepository borrowerRepository;

    @Autowired
    private BookInformationRepositoryPeer bookInformationRepositoryPeer;

    @Autowired
    private BookRepositoryPeer bookRepositoryPeer;

    @Autowired
    private BorrowerRepositoryPeer borrowerRepositoryPeer;

    @Autowired
    private CheckOutRecordRepositoryPeer checkOutRecordRepositoryPeer;

    @Autowired
    private ReservationRecordRepositoryPeer reservationRecordRepositoryPeer;

    private BookInformationRepository bookInformationRepository;
    private BookRepository bookRepository;
    private CheckOutRecordRepository checkOutRecordRepository;
    private ReservationRecordRepository reservationRecordRepository;

    @BeforeEach
    void setUp() {
        bookInformationRepository = new BookInformationRepository(bookInformationRepositoryPeer);
        bookRepository = new BookRepository(bookRepositoryPeer);
        borrowerRepository = new BorrowerRepository(borrowerRepositoryPeer);
        checkOutRecordRepository = new CheckOutRecordRepository(checkOutRecordRepositoryPeer);
        reservationRecordRepository = new ReservationRecordRepository(reservationRecordRepositoryPeer);

        ISBN = UUID.randomUUID().toString();
        collection = new Collection(bookInformationRepository, bookRepository);
        recordManager = new RecordManager(checkOutRecordRepository, reservationRecordRepository);
    }
}
