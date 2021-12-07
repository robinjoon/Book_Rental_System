package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.BookDao;
import dto.Book;

public class BookService {
	
	@Autowired
	private BookDao bookDao;
	
	public int registBook(Book book) {
		return bookDao.insertBook(book);
	}
	public boolean deleteBook(int bookId) {
		return bookDao.deleteBook(bookId);
	}
	public List<Book> getBookList() {
		return bookDao.selectBooks();
	}
	public List<Book> getBookList(String category) {
		return bookDao.selectBooks(category);
	}
	public Book getBook(int bookId) {
		return bookDao.selectBook(bookId);
	}
}
