package ntut.edu.tw.bookerfly.config;

import ntut.edu.tw.bookerfly.entity.collection.Collection;
import ntut.edu.tw.bookerfly.entity.record.RecordManager;
import ntut.edu.tw.bookerfly.respository.BookInformationRepository;
import ntut.edu.tw.bookerfly.respository.BookRepository;
import ntut.edu.tw.bookerfly.respository.CheckOutRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter({RepositoryInjection.class})
public class EntityInjection {
    @Autowired
    private BookInformationRepository bookInformationRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CheckOutRecordRepository checkOutRecordRepository;

    @Bean
    public Collection collection() {
        return new Collection(bookInformationRepository, bookRepository);
    }

    @Bean
    public RecordManager recordManager() {
        return new RecordManager(checkOutRecordRepository);
    }
}