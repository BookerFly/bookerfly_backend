package ntut.edu.tw.bookerfly.handler;

import ntut.edu.tw.bookerfly.entity.user.Borrower;
import ntut.edu.tw.bookerfly.respository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterBorrowerController {
    @Autowired
    private BorrowerRepository borrowerRepository;

    @PostMapping(path = "bookerfly/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> register(@RequestBody String userInfo) {
        try {
            JSONObject userJson = new JSONObject(userInfo);
            String userId = userJson.getString("userId");
            borrowerRepository.save(new Borrower(userId));
            return new ResponseEntity<>("Success to register borrower.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to register borrower, caused by " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
