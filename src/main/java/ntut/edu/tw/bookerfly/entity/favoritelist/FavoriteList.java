package ntut.edu.tw.bookerfly.entity.favoritelist;

import ntut.edu.tw.bookerfly.entity.collection.BookInformation;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "favorite_list")
public class FavoriteList {
    @Id
    @Column(name = "favorite_list_id")
    private String favoriteListId;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "book_info_in_favorite_list",
            joinColumns = @JoinColumn(name = "favorite_list_id"),
            inverseJoinColumns = @JoinColumn(name = "book_info_id"))
    private Set<BookInformation> bookInfoList;

    public FavoriteList() {
        favoriteListId = UUID.randomUUID().toString();
        bookInfoList = new HashSet<>();
    }

    public String getFavoriteListId() {
        return favoriteListId;
    }

    public boolean isEmpty() {
        return bookInfoList.isEmpty();
    }

    public void add(BookInformation bookInformation) {
        if (!bookInfoList.stream().anyMatch(x -> x.getBookInfoId().equals(bookInformation.getBookInfoId()))){
            bookInfoList.add(bookInformation);
        }
    }

    public void remove(BookInformation bookInformation) {
        bookInfoList.removeIf(x -> x.getBookInfoId().equals(bookInformation.getBookInfoId()));
    }

    public Set<BookInformation> getBookInfoList() {
        return bookInfoList;
    }
}
