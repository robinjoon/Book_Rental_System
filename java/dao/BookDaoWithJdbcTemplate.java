package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import dto.Book;

public class BookDaoWithJdbcTemplate implements BookDao {

	private JdbcTemplate jdbcTemplate;
	
	public BookDaoWithJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int insertBook(Book book) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
			jdbcTemplate.update((Connection con) -> {
				String sql = "insert into book(category,title,writer,translator,publisher,image,isbn) values(?,?,?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, book.getCategory());
				pstmt.setString(2, book.getTitle());
				pstmt.setString(3, book.getWriter());
				pstmt.setString(4, book.getTranslator());
				pstmt.setString(5, book.getPublisher());
				pstmt.setString(6, book.getImage());
				pstmt.setString(7, book.getIsbn());
				return pstmt;
				},keyHolder);
			int bookId = keyHolder.getKey().intValue();
			return bookId;
		}catch(Exception e) {
			return -1;
		}
	}

	@Override
	public boolean deleteBook(int bookId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Book> selectBooks() {
		String sql = "select * from book";
		List<Book> result = jdbcTemplate.query(sql, new BookRowMapper<Book>());
		return result;
	}

	@Override
	public List<Book> selectBooks(String category) {
		String sql = "select * from book where category = ?";
		List<Book> result = jdbcTemplate.query(sql, new BookRowMapper<Book>(),category);
		return result;
	}

	@Override
	public Book selectBook(int bookId) {
		String sql = "select * from book where book_id = ?";
		List<Book> result = jdbcTemplate.query(sql, new BookRowMapper<Book>(),bookId);
		return result.get(0);
	}
	private class BookRowMapper<T extends Book> implements RowMapper<T>{
		@SuppressWarnings("unchecked")
		@Override
		public T mapRow(ResultSet rs, int rowNum) throws SQLException {
			Book.Builder builder = new Book.Builder(
					rs.getString("category"),
					rs.getString("title"), 
					rs.getString("writer"), 
					rs.getString("publisher"), 
					rs.getString("image"));
			builder.setBookId(rs.getInt("book_id"))
			.setIsbn(rs.getString("isbn"))
			.setTranslator(rs.getString("translator"));
			return (T)builder.build(); 
		}
	}
}
