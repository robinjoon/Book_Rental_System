package authentication;

import org.springframework.beans.factory.annotation.Autowired;

import services.MemberService;

public class AuthManager {

	@Autowired
	private MemberService memberService;
	
	public String createJwt(String id, String pw) {
		if(memberService.login(id, pw)) {
			// create JWT
		}
		return null;
	}
	public boolean verifyJwt(String jwt) {
		return false;
	}
	public Object updateJwt() {
		return null;
	}
}
