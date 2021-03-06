package dao;

import dto.*;
import java.util.*;

public interface BookDao {
	
	int insertBook(Book book);
	boolean deleteBook(int bookId);
	List<Book> selectBooks();
	List<Book> selectBooks(String category);
	Book selectBook(int bookId);
}
