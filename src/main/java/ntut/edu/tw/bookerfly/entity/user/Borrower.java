package ntut.edu.tw.bookerfly.entity.user;

public class Borrower {
    private String userId;
    private int loanItemCount;

    public Borrower(String userId) {
        this.userId = userId;
        loanItemCount = 0;
    }

    public String getUserId() {
        return userId;
    }

    public boolean hasBorrowQualification() {
        return loanItemCount < 3;
    }

    public void increaseLoanItemCount() {
        loanItemCount++;
    }
}