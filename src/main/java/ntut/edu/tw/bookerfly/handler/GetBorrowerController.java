package ntut.edu.tw.bookerfly.handler;

import ntut.edu.tw.bookerfly.entity.user.Borrower;
import ntut.edu.tw.bookerfly.entity.user.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class GetBorrowerController {
    private Organization organization;

    @Autowired
    public GetBorrowerController(Organization organization) {
        this.organization = organization;
    }

    @GetMapping(path = "bookerfly/organization/borrowers", produces = "application/json")
    public ResponseEntity<Object> getBorrower(@RequestParam String userId) {
        try {
            Optional<Borrower> borrower = organization.getBorrower(userId);
            if(borrower.isPresent())
                return new ResponseEntity<>(borrower.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>("Failed to get borrower, borrower not found." , HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get borrower, caused by " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
