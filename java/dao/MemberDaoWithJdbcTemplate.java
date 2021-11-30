package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import dto.*;

public class MemberDaoWithJdbcTemplate implements MemberDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public MemberDaoWithJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public boolean insertMember(Member member) {
		try {
			int result = jdbcTemplate.update((Connection con) -> {
				String sql = "insert into Member values(?,?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getId());
				pstmt.setString(2, member.getName());
				pstmt.setString(3, member.getPw());
				pstmt.setBoolean(4, member.isAdmin());
				pstmt.setInt(5, member.getOverDueCount());
				pstmt.setBoolean(6, member.isBeingRented());
				return pstmt;
				});
			return result == 1 ? true : false;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public Member selectMember(String email) {
		String sql = "select * from member where email = ?";
		Member member;
		try{
			member = jdbcTemplate.query(sql, new MemberRowMapper<Member>(),email).get(0);
		}catch(Exception e) {
			return null;
		}
		sql = "select * from book where book_id = (select * from rental_list where member_email = ? and returned = FALSE)";
		List<Book> books = jdbcTemplate.query(sql, (ResultSet rs, int rowNum) ->{
			Book.Builder builder = new Book.Builder(
					rs.getString("category"),
					rs.getString("title"), 
					rs.getString("writer"), 
					rs.getString("publisher"), 
					rs.getString("image"));
			builder.setBookId(rs.getInt("book_id"))
			.setIsbn(rs.getString("isbn"))
			.setTranslator(rs.getString("translator"));
			return builder.build(); 
		}, email);
		member.setRentalList(books);
		return member;
	}

	@Override
	public boolean updateMember(Member member) {
		String sql = "update member set name = ?, pw = ? , "
				+ "is_admin = ?, overdue_count = ?, being_rented = ?"
				+ "where email = ?";
		int result = jdbcTemplate.update((Connection con) -> {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(6, member.getId());
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPw());
			pstmt.setBoolean(3, member.isAdmin());
			pstmt.setInt(4, member.getOverDueCount());
			pstmt.setBoolean(5, member.isBeingRented());
			return pstmt;
		});
		return result == 1 ? true : false;
	}

	@Override
	public List<Member> selectMembers() {
		String sql = "select * from member";
		List<Member> result = jdbcTemplate.query(sql, new MemberRowMapper<Member>());
		return result;
	}

	@Override
	public boolean deleteMember(String email) {
		String sql = "delete from member where email = ?";
		int result = jdbcTemplate.update(sql, email);
		return result == 1 ? true : false;
	}
	
	private class MemberRowMapper<T extends Member> implements RowMapper<T>{
		@SuppressWarnings("unchecked")
		@Override
		public T mapRow(ResultSet rs, int rowNum) throws SQLException {
			Member.Builder builder = 
					new Member.Builder(rs.getString("email"),rs.getString("name"), rs.getString("pw"));
			
			builder.setAdmin(rs.getBoolean("is_admin"));
			builder.setBeingRented(rs.getBoolean("being_rented"));
			builder.setOverDueCount(rs.getInt("overdue_count"));
			
			return (T) builder.build();
		}
	}

}
