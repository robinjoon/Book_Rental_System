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

public class MemberDaoForMariaDB implements MemberDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public MemberDaoForMariaDB(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public boolean insertMember(Member member) {
		int result = jdbcTemplate.update((Connection con) -> {
			String sql = "insert into Member values(?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(0, member.getEmail());
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
	public Member selectMember(String email) {
		String sql = "select * from member where email = ?";
		Member result = jdbcTemplate.query(sql, new MemberRowMapper<Member>()).get(0);
		return result;
	}

	@Override
	public boolean updateMember(Member member) {
		String sql = "update member set name = ?, pw = ? , "
				+ "is_admin = ?, overdue_count = ?, being_rented = ?"
				+ "where email = ?";
		int result = jdbcTemplate.update((Connection con) -> {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(5, member.getEmail());
			pstmt.setString(0, member.getName());
			pstmt.setString(1, member.getPw());
			pstmt.setBoolean(2, member.isAdmin());
			pstmt.setInt(3, member.getOverDueCount());
			pstmt.setBoolean(4, member.isBeingRented());
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