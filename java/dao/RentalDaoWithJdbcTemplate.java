package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import dto.Book;
import dto.Rental;

public class RentalDaoWithJdbcTemplate implements RentalDao {

	private JdbcTemplate jdbcTemplate;
	
	public RentalDaoWithJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean insertRental(Rental rental) {
		String sql = "insert into rental_list(book_id,user_id,due_time) values(?,?,?)";
		int result = jdbcTemplate.update(sql, rental.getBookId(),rental.getUserId(), Timestamp.valueOf(LocalDateTime.now().plusDays(14)));
		if(result == 1) 
			return true;
		else
			return false;
	}

	@Override
	public boolean updateRental(Rental rental) {
		String sql = "update rental_list set due_time = ?";
		int result = jdbcTemplate.update(sql, rental.getDueTime());
		if(result == 1) 
			return true;
		else
			return false;
	}

	@Override
	public boolean patchRental(Rental rental) {
		String sql = "update rental_list set returned = ?";
		int result = jdbcTemplate.update(sql, rental.isReturned());
		if(result == 1) 
			return true;
		else
			return false;
	}

	@Override
	public List<Rental> selectRentals(int bookId) {
		String sql = "select * from rental_list where book_id = ?";
		List<Rental> result = jdbcTemplate.query(sql, new RentalRowMapper<Rental>(),bookId);
		return result;
	}

	@Override
	public List<Rental> selectRentals(String userID) {
		String sql = "select * from rental_list where user_id = ?";
		List<Rental> result = jdbcTemplate.query(sql, new RentalRowMapper<Rental>(),userID);
		return result;
	}

	@Override
	public Rental selectRental(int rentalId) {
		String sql = "select * from rental_list where rental_id = ?";
		List<Rental> result = jdbcTemplate.query(sql, new RentalRowMapper<Rental>(),rentalId);
		return result.get(0);
	}
	
	private class RentalRowMapper<T extends Rental> implements RowMapper<T>{
		@SuppressWarnings("unchecked")
		@Override
		public T mapRow(ResultSet rs, int rowNum) throws SQLException {
			Rental.Builder builder = new Rental.Builder(rs.getString("user_id"), rs.getTimestamp("start_time"), rs.getTimestamp("due_time"));
			return (T)builder.setBookId(rs.getInt("book_id"))
			.setRentalId(rs.getInt("rental_id"))
			.setReturned(rs.getBoolean("returned"))
			.build();
			
		}
	}
}
