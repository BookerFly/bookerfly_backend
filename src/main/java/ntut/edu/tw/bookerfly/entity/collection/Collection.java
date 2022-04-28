package ntut.edu.tw.bookerfly.entity.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Collection {
    private List<BookInformation> bookInfos;
    private List<Book> books;

    public Collection() {
        bookInfos = new ArrayList<>();
        books = new ArrayList<>();
    }

    public void createBook(String title, String author, String ISBN, String image, String type, String bookshelfPosition, int bookshelfNumber, int count) {
        Optional<BookInformation> bookInfo = getBookInformation(title, author, ISBN, image, type);
        String bookInfoId;

        if (bookInfo.isPresent()) {
            bookInfoId = bookInfo.get().getBookInfoId();
        } else {
            BookInformation bookInformation = new BookInformation(title, author, ISBN, image, type);
            bookInfos.add(bookInformation);
            bookInfoId = bookInformation.getBookInfoId();
        }

        for (int i = 0; i < count; i++) {
            books.add(new Book(bookInfoId, BookStatus.AVAILABLE, bookshelfPosition, bookshelfNumber));
        }
    }

    public List<Book> selectBook(String bookInfoId) {
        List<Book> searchResult = new ArrayList<>();

        for (Book book : books) {
            if (book.hasSameBookInfo(bookInfoId))
                searchResult.add(book);
        }

        return searchResult;
    }

    public void borrowBook(String bookId) {
        Book book = books.stream().filter(x -> x.getBookId().equals(bookId)).findFirst().get();

        if (book.getBookStatus().equals(BookStatus.CHECKED_OUT))
            throw new RuntimeException("The book is already checked out.");

        book.setBookStatus(BookStatus.CHECKED_OUT);
    }

    public Optional<BookInformation> getBookInformationById(String bookInfoId) {
        return bookInfos.stream().filter(x -> x.getBookInfoId().equals(bookInfoId)).findFirst();
    }

    public int getCount() {
        return books.size();
    }

    public List<BookInformation> getAllBookInformations() {
        return bookInfos;
    }

    public List<Book> getAllBooks() {
        return books;
    }

    private Optional<BookInformation> getBookInformation(String title, String author, String ISBN, String image, String type) {
        return bookInfos.stream().filter(x -> x.getTitle().equals(title) &&
                x.getAuthor().equals(author) &&
                x.getISBN().equals(ISBN) &&
                x.getImage().equals(image) &&
                x.getType().equals(type)).findFirst();
    }
}