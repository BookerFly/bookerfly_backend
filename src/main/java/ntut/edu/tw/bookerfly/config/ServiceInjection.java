package ntut.edu.tw.bookerfly.config;

import ntut.edu.tw.bookerfly.usecase.SearchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceInjection {
    @Bean
    public SearchService searchService() {
        return new SearchService();
    }
}