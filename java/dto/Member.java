package dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter

public class Member {
	private String id;
	private String name;
	private String pw;
	private int overDueCount;
	private boolean isAdmin;
	private boolean beingRented;
	@Setter
	private List<Book> rentalList;
	
	private Member() {}
	
	public static class Builder{
		private String id;
		private String name;
		private String pw;
		private int overDueCount = 0;
		private boolean isAdmin = false;
		private boolean beingRented = false;
		
		public Builder(String email, String name, String pw) {
			this.id = email;
			this.name = name;
			this.pw = pw;
		}

		public Builder setOverDueCount(int overDueCount) {
			this.overDueCount = overDueCount;
			return this;
		}

		public Builder setAdmin(boolean isAdmin) {
			this.isAdmin = isAdmin;
			return this;
		}

		public Builder setBeingRented(boolean beingRented) {
			this.beingRented = beingRented;
			return this;
		}
		
		public Member build() {
			Member member = new Member();
			member.beingRented = beingRented;
			member.id = id;
			member.isAdmin = isAdmin;
			member.name = name;
			member.overDueCount = overDueCount;
			member.pw = pw;
			return member;
		}
		
	}
	

	@Override
	public String toString() {
		return "Member [email=" + id + ", name=" + name + ", pw=" + pw + ", overDueCount=" + overDueCount
				+ ", isAdmin=" + isAdmin + ", beingRented=" + beingRented + "]";
	}	
	
}
