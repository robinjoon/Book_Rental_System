package dto;

import lombok.Getter;

@Getter
public class Book {
	private int bookId;
	private String category;
	private String title;
	private String writer;
	private String translator;
	private String publisher;
	private String image;
	private String isbn;
	
	private Book() {}
	
	public static class Builder{
		private int bookId;
		private String category;
		private String title;
		private String writer;
		private String translator;
		private String publisher;
		private String image;
		private String isbn;
		
		public Builder(String category, String title, String writer, String publisher, String image) {
			this.category = category;
			this.title = title;
			this.writer = writer;
			this.publisher = publisher;
			this.image = image;
		}

		public Builder setBookId(int bookId) {
			this.bookId = bookId;
			return this;
		}

		public Builder setTranslator(String translator) {
			this.translator = translator;
			return this;
		}

		public Builder setIsbn(String isbn) {
			this.isbn = isbn;
			return this;
		}

		
		public Book build() {
			Book book = new Book();
			book.bookId = bookId;
			book.category = category;
			book.image = image;
			book.isbn = isbn;
			book.publisher = publisher;
			book.title = title;
			book.translator = translator;
			book.writer = writer;
			return book;
		}
		
	}
	
}
