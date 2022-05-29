package ntut.edu.tw.bookerfly.config;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryInjection {

    private BorrowerRepositoryPeer borrowerRepositoryPeer;
    private BookRepositoryPeer bookRepositoryPeer;
    private BookInformationRepositoryPeer bookInformationRepositoryPeer;
    private CheckOutRecordRepositoryPeer checkOutRecordRepositoryPeer;
    private ReservationRecordRepositoryPeer reservationRecordRepositoryPeer;

    @Autowired
    public RepositoryInjection(BorrowerRepositoryPeer borrowerRepositoryPeer,
                               BookRepositoryPeer bookRepositoryPeer,
                               BookInformationRepositoryPeer bookInformationRepositoryPeer,
                               CheckOutRecordRepositoryPeer checkOutRecordRepositoryPeer,
                               ReservationRecordRepositoryPeer reservationRecordRepositoryPeer) {
        this.borrowerRepositoryPeer = borrowerRepositoryPeer;
        this.bookRepositoryPeer = bookRepositoryPeer;
        this.bookInformationRepositoryPeer = bookInformationRepositoryPeer;
        this.checkOutRecordRepositoryPeer = checkOutRecordRepositoryPeer;
        this.reservationRecordRepositoryPeer = reservationRecordRepositoryPeer;
    }

    @Bean
    public BorrowerRepository borrowerRepository() {
        return new BorrowerRepository(borrowerRepositoryPeer);
    }

    @Bean
    public BookRepository bookRepository() {
        return new BookRepository(bookRepositoryPeer);
    }

    @Bean
    public BookInformationRepository bookInformationRepository() {
        return new BookInformationRepository(bookInformationRepositoryPeer);
    }

    @Bean
    public CheckOutRecordRepository checkOutRecordRepository() {
        return new CheckOutRecordRepository(checkOutRecordRepositoryPeer);
    }

    @Bean
    public ReservationRecordRepository reservationRecordRepository() {
        return new ReservationRecordRepository(reservationRecordRepositoryPeer);
    }
}
