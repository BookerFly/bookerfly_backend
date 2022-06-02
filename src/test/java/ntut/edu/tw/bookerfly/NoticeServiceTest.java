package ntut.edu.tw.bookerfly;

import ntut.edu.tw.bookerfly.service.NoticeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NoticeServiceTest {
    @Autowired
    private NoticeService senderService;

    @Test
    public void test_email() {
        senderService.sendEmail("bookerfly.csie.ntut@gmail.com", "HI", "test content");
    }
}
