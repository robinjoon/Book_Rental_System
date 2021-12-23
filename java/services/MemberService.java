package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import dao.MemberDao;
import dto.Member;

public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	public boolean registMember(Member member) {
		return memberDao.insertMember(member);
	}
	
	public Member getMyInfo(String userId) {
		return memberDao.selectMember(userId);
	}
	
	public boolean updateMyInfo(Member member) {
		return memberDao.updateMember(member);
	}
	
	public List<Member> getMemberList(){
		return memberDao.selectMembers();
	}
	
	public Member getMember(String userId) {
		return memberDao.selectMember(userId);
	}
	
	public Member login(String userId, String pw) {
		return memberDao.loginMember(userId, pw);
	}
}
