package ntut.edu.tw.bookerfly.entity.user;

import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import ntut.edu.tw.bookerfly.respository.user.BorrowerRepository;
import java.util.List;
import java.util.Optional;

public class Organization {
    private List<Borrower> borrowers;
    private Manager manager;

    private BorrowerRepository borrowerRepository;

    public Organization(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    public Optional<Borrower> getBorrower(String userId) {
        return borrowerRepository.findById(userId);
    }

    public void addBorrower(Borrower borrower) {
        borrowerRepository.save(borrower);
    }

    public void addFavoriteBook(String userId, BookInformation bookInformation) {
        Optional<Borrower> borrower = getBorrower(userId);

        if (borrower.isEmpty()) {
            throw new RuntimeException("This borrower is not existed.");
        }

        borrower.get().addFavoriteBook(bookInformation);
        borrowerRepository.save(borrower.get());
    }

    public void removeFavoriteBook(String userId, BookInformation bookInformation) {
        Optional<Borrower> borrower = getBorrower(userId);

        if (borrower.isEmpty()) {
            throw new RuntimeException("This borrower is not existed.");
        }

        borrower.get().removeFavoriteBook(bookInformation);
        borrowerRepository.save(borrower.get());
    }
}
