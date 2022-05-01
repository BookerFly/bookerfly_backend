package ntut.edu.tw.bookerfly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookerflyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookerflyApplication.class, args);

		System.out.println("Bookerfly running...");
	}
}
