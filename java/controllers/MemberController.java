package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dto.Member;
import services.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("")
	private ResponseEntity<?> registMember(@RequestBody Member member) {
		if(memberService.registMember(member)) {
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	@GetMapping("/{email}")
	private ResponseEntity<Member> getMember(@PathVariable("email") String email) {
		Member member = memberService.getMember(email);
		if(member == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(member);
		}
	}
	
	@GetMapping("")
	private ResponseEntity<List<Member>> getMembers(){
		List<Member> memberList = memberService.getMemberList();
		if(memberList == null) {
			return ResponseEntity.badRequest().build();
		}else {
			return ResponseEntity.ok(memberList);
		}
		
	}
	@GetMapping("/myInfo")
	private ResponseEntity<Member> getMyInfo(){
		String userId = ""; // get userId from JWT
		Member member = memberService.getMember(userId);
		if(member == null) {
			return ResponseEntity.badRequest().build();
		}else {
			return ResponseEntity.ok(member);
		}
	}
}
