package ntut.edu.tw.bookerfly.entity.collection;

import ntut.edu.tw.bookerfly.respository.BookInformationRepository;
import ntut.edu.tw.bookerfly.respository.BookRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Collection {
    private List<BookInformation> bookInfos;
    private List<Book> books;

    private BookInformationRepository bookInformationRepository;
    private BookRepository bookRepository;

    public Collection(BookInformationRepository bookInformationRepository, BookRepository bookRepository) {
        this.bookInformationRepository = bookInformationRepository;
        this.bookRepository = bookRepository;
        bookInfos = bookInformationRepository.findAll();
        books = bookRepository.findAll();
    }

    public void createBook(String title, String author, String ISBN, String image, String type, String bookshelfPosition, int bookshelfNumber, int count) {
        if (getBookInformation(title, author, ISBN, image, type).isEmpty()) {
            BookInformation bookInformation = new BookInformation(title, author, ISBN, image, type);
            bookInformationRepository.save(bookInformation);
            bookInfos.add(bookInformation);
        }

        for (int i = 0; i < count; i++) {
            Book book = new Book(getBookInformation(title, author, ISBN, image, type).get(),
                                    BookStatus.AVAILABLE,
                                    bookshelfPosition,
                                    bookshelfNumber);
            bookRepository.save(book);
            books.add(book);
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

    public List<Book> selectBook(String title, String author, String isbn, String image, String type) {
        List<Book> searchResult = new ArrayList<>();

        for (Book book : books) {
            if (book.hasSameBookInfo(title, author, isbn, image, type))
                searchResult.add(book);
        }

        return searchResult;
    }

    public void borrowBook(String bookId) {
        Book book = books.stream().filter(x -> x.getBookId().equals(bookId)).findFirst().get();

        if (book.getBookStatus().equals(BookStatus.CHECKED_OUT))
            throw new RuntimeException("The book is already checked out.");

        book.setBookStatus(BookStatus.CHECKED_OUT);
        bookRepository.save(book);
    }

    public void returnBook(String bookId) {
        Book book = books.stream().filter(x -> x.getBookId().equals(bookId)).findFirst().get();

        book.setBookStatus(BookStatus.PROCESSING);
        bookRepository.save(book);
    }

    public void confirmBookReturned(String bookId) {
        Book book = books.stream().filter(x -> x.getBookId().equals(bookId)).findFirst().get();

        book.setBookStatus(BookStatus.AVAILABLE);
        bookRepository.save(book);
    }

    public void lostBook(String bookId) {
        Book book = books.stream().filter(x -> x.getBookId().equals(bookId)).findFirst().get();

        book.setBookStatus(BookStatus.MISSING);
        bookRepository.save(book);
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