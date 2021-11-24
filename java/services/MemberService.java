package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dao.MemberDao;
import dto.Member;

public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	public ResponseEntity<Member> registMember(Member member) {
		if(memberDao.insertMember(member)) {
			return ResponseEntity.ok(member); 
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
	
	public ResponseEntity<Member> getMyInfo(String email) {
		Member member = memberDao.selectMember(email);
		if(member == null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(member);
	}
	public ResponseEntity<Member> updateMyInfo(Member member) {
		if(memberDao.updateMember(member)) {
			Member getMember = memberDao.selectMember(member.getId());
			if(getMember == null)
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // required fix http status code
			else
				return ResponseEntity.ok(member);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	public ResponseEntity<List<Member>> getMemberList(){
		List<Member> memberList = memberDao.selectMembers();
		if(memberList==null || memberList.isEmpty()) {
			return ResponseEntity.status(500).build();
		}else {
			return ResponseEntity.ok(memberList);
		}
	}
	public ResponseEntity<Member> getMember(String email) {
		Member member = memberDao.selectMember(email);
		if(member == null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(member);
	}
}
