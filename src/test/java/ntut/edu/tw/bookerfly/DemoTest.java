package ntut.edu.tw.bookerfly;

import ntut.edu.tw.bookerfly.entity.user.Borrower;
import org.junit.jupiter.api.Test;

public class DemoTest extends AbstractSpringJpaTest{
    @Test
    public void prepare_demo_data() {
        borrowerRepository.save(new Borrower("userId", "Jay", "test@gmail.com"));

        collection.createBook("OOAD", "Larman", "ISBN", "", "Book", "Lab1321", 1, 2);
        collection.createBook("OOAD", "Larman", "ISBN", "", "Book", "Lab1324", 2, 1);
        collection.createBook("OOAD", "Larman", "ISBN", "", "Book", "Lab1324", 3, 1);
        collection.createBook("UML", "Martin", "ISBN2", "", "Book", "Lab1321", 3, 1);
        collection.createBook("DDD", "Eric", "ISBN3", "", "Book", "Lab1324", 2, 3);
        collection.createBook("A Method to Ensure Correctness Of Aggregate", "Somebody1", "", "", "Paper", "Lab1321", 2, 1);
        collection.createBook("Separate Read and Write Models", "Somebody2", "", "", "Paper", "Lab1321", 2, 1);
        collection.createBook("Implement DDD Repository In CA", "Somebody3", "", "", "Paper", "Lab1321", 2, 1);
        collection.createBook("Design Patterns", "GoF", "ISBN4", "", "Book", "Lab1321", 4, 2);
        collection.createBook("CA", "Robert", "ISBN5", "", "Book", "Lab1321", 3, 1);
        collection.createBook("DS", "Steen", "ISBN6", "", "Book", "Lab1324", 2, 1);
        collection.createBook("Learning Agile", "Stellman", "ISBN7", "", "Book", "Lab1321", 4, 2);
        collection.createBook("SbE", "Gojko", "ISBN8", "", "Book", "Lab1321", 3, 1);
        collection.createBook("UT", "Vladmir", "ISBN9", "", "Book", "Lab1324", 2, 1);
    }
}
