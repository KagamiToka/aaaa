package service.impl;

import model.Book;
import repository.book.BookRepository;
import service.IBookService;
import service.IService;

import java.util.List;

public class BookService implements IService<Book>, IBookService {
    private static BookRepository bookRepository = new BookRepository();

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public void remove(int id) {
        bookRepository.delete(id);
    }

    @Override
    public void update(int id, Book book) {
        bookRepository.update(book);
    }

    @Override
    public void update(Book book) {
        bookRepository.update(book);
    }

    @Override
    public Book findById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public void add(Book book) {
        bookRepository.add(book);
    }

    @Override
    public List<Book> findByAuthor(String name) {
        return bookRepository.findByAuthor(name);
    }

    @Override
    public List<Book> findByAuthorFr() {
        return bookRepository.findByAuthorFr();
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.update(book);
    }

    @Override
    public List<Book> findByPrice(double minPrice, double maxPrice) {
        return bookRepository.findByPrice(minPrice, maxPrice);
    }

    // Thêm phương thức cập nhật stockQuantity
    public void updateStockQuantity(int bookId, int quantity) {
        Book book = findById(bookId);
        if (book != null) {
            int newStock = book.getStockQuantity() - quantity;
            if (newStock < 0) {
                throw new IllegalStateException("Không đủ hàng trong kho cho sách ID: " + bookId);
            }
            book.setStockQuantity(newStock);
            update(book);
        }
    }
}