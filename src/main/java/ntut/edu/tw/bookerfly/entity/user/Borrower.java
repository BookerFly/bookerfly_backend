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

    @Column(name="email")
    private String email;

    @Column(name="nick_name")
    private String nickName;

    @Column(name = "loan_item_count")
    private int loanItemCount;

    public Borrower() {
    }

    public Borrower(String userId, String nickName, String email) {
        this.userId = userId;
        this.nickName = nickName;
        this.email = email;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLoanItemCount() {
        return loanItemCount;
    }

    public void setLoanItemCount(int loanItemCount) {
        this.loanItemCount = loanItemCount;
    }
}