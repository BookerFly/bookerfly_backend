package ntut.edu.tw.bookerfly.entity.user;

import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import ntut.edu.tw.bookerfly.entity.favoritelist.FavoriteList;

import javax.persistence.*;

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

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinTable(name = "favorite_list_in_borrower",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "favorite_list_id"))
    private FavoriteList favoriteList;

    public Borrower() {
    }

    public Borrower(String userId, String nickName, String email) {
        this.userId = userId;
        this.nickName = nickName;
        this.email = email;
        loanItemCount = 0;
        favoriteList = new FavoriteList();
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

    public FavoriteList getFavoriteList() {
        return favoriteList;
    }

    public void addFavoriteBook(BookInformation bookInformation) {
        favoriteList.add(bookInformation);
    }

    public void removeFavoriteBook(BookInformation bookInformation) {
        favoriteList.remove(bookInformation);
    }
}