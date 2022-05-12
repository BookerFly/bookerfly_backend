package ntut.edu.tw.bookerfly.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "borrower")
public class Borrower {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "loan_item_count")
    private int loanItemCount;

    public Borrower() {
    }

    public Borrower(String userId) {
        this.userId = userId;
        loanItemCount = 0;
    }

    public boolean hasBorrowQualification() {
        return loanItemCount < 3;
    }

    public void increaseLoanItemCount() {
        loanItemCount++;
    }

    public void decreaseLoanItemCount() {
        loanItemCount--;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getLoanItemCount() {
        return loanItemCount;
    }

    public void setLoanItemCount(int loanItemCount) {
        this.loanItemCount = loanItemCount;
    }
}