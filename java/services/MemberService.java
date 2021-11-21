package services;

import org.springframework.beans.factory.annotation.Autowired;

import dao.MemberDao;
import dto.Member;

public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	public Member registMember(Member member) {
		if(memberDao.insertMember(member)) {
			return member;
		}else {
			return new Member.Builder(null, null, null).build();
		}
	}
	public Object getMyInfo() {
		return null;
	}
	public boolean updateMyInfo() {
		return true;
	}
	public Object getMemberList(){
		return null;
	}
	public Object getMember() {
		return null;
	}
}
