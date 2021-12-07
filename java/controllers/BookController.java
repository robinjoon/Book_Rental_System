package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.Book;
import services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("")
	private ResponseEntity<Book> registBook(@RequestBody Book book){
		int bookId = bookService.registBook(book);
		if(bookId != -1) {
			book = bookService.getBook(bookId);
			return ResponseEntity.ok(book);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/{bookId}")
	private ResponseEntity<Book> getBook(@PathVariable("{bookId}") int bookId){
		Book book = bookService.getBook(bookId);
		if(book != null) {
			return ResponseEntity.ok(book);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/{category}")
	private ResponseEntity<List<Book>> updateBook(@PathVariable("{category}") String category){
		List<Book> bookList = bookService.getBookList(category);
		if(bookList != null) {
			return ResponseEntity.ok(bookList);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/{bookId}")
	private ResponseEntity<?> deleteBook(@PathVariable("{bookId}") int bookId){
		if(bookService.deleteBook(bookId)) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
}
