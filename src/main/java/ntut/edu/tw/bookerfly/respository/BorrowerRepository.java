package ntut.edu.tw.bookerfly.respository;

import ntut.edu.tw.bookerfly.entity.user.Borrower;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BorrowerRepository {
    private List<Borrower> borrowers;

    public BorrowerRepository() {
        borrowers = new ArrayList<>();
    }

    public Optional<Borrower> findById(String userId) {
        return Optional.empty();
    }
}