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

    @Autowired
    public RepositoryInjection(BorrowerRepositoryPeer borrowerRepositoryPeer, BookRepositoryPeer bookRepositoryPeer, BookInformationRepositoryPeer bookInformationRepositoryPeer) {
        this.borrowerRepositoryPeer = borrowerRepositoryPeer;
        this.bookRepositoryPeer = bookRepositoryPeer;
        this.bookInformationRepositoryPeer = bookInformationRepositoryPeer;
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
}
