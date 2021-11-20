package dto;

import lombok.Getter;

@Getter
public class Member {
	private String email;
	private String name;
	private String pw;
	private int overDueCount;
	private boolean isAdmin;
	private boolean beingRented;
	
	private Member() {}
	
	public static class Builder{
		private String email;
		private String name;
		private String pw;
		private int overDueCount = 0;
		private boolean isAdmin = false;
		private boolean beingRented = false;
		
		public Builder(String email, String name, String pw) {
			this.email = email;
			this.name = name;
			this.pw = pw;
		}

		public void setOverDueCount(int overDueCount) {
			this.overDueCount = overDueCount;
		}

		public void setAdmin(boolean isAdmin) {
			this.isAdmin = isAdmin;
		}

		public void setBeingRented(boolean beingRented) {
			this.beingRented = beingRented;
		}
		
		public Member build() {
			Member member = new Member();
			member.beingRented = beingRented;
			member.email = email;
			member.isAdmin = isAdmin;
			member.name = name;
			member.overDueCount = overDueCount;
			member.pw = pw;
			return member;
		}
		
	}	
}
