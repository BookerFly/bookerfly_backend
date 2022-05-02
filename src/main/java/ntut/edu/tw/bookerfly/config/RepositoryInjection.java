package ntut.edu.tw.bookerfly.config;

import ntut.edu.tw.bookerfly.respository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryInjection {

    private BorrowerRepositoryPeer borrowerRepositoryPeer;
    private BookRepositoryPeer bookRepositoryPeer;
    private BookInformationRepositoryPeer bookInformationRepositoryPeer;
    private CheckOutRecordRepositoryPeer checkOutRecordRepositoryPeer;

    @Autowired
    public RepositoryInjection(BorrowerRepositoryPeer borrowerRepositoryPeer, BookRepositoryPeer bookRepositoryPeer, BookInformationRepositoryPeer bookInformationRepositoryPeer, CheckOutRecordRepositoryPeer checkOutRecordRepositoryPeer) {
        this.borrowerRepositoryPeer = borrowerRepositoryPeer;
        this.bookRepositoryPeer = bookRepositoryPeer;
        this.bookInformationRepositoryPeer = bookInformationRepositoryPeer;
        this.checkOutRecordRepositoryPeer = checkOutRecordRepositoryPeer;
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
}
