package ntut.edu.tw.bookerfly.service;

import ntut.edu.tw.bookerfly.entity.collection.Book;
import ntut.edu.tw.bookerfly.entity.user.Borrower;
import ntut.edu.tw.bookerfly.respository.collection.BookRepository;
import ntut.edu.tw.bookerfly.respository.user.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoticeService {
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private BookRepository bookRepository;

    public void notice(String bookId, String userId) {
        Optional<Borrower> user = borrowerRepository.findById(userId);
        Optional<Book> book = bookRepository.findById(bookId);
        if(user.isEmpty() || book.isEmpty()) {
            throw new RuntimeException();
        }
        String body = """
                Hi %s, 
                This is Bookerfly's notice service :)
                Your %s is ready for you.
                
                BR,
                Bookerfly
                """.formatted(user.get().getNickName(), book.get().getTitle());

        sendEmail(user.get().getEmail(), "Reserved Book is Available", body);
    }

    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);

        System.out.println("Mail sent successfully...");
    }
}
