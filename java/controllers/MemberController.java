package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.Member;
import services.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/regist")
	private Member registMember(@RequestBody Member member) {
		System.out.println("*******************************");
		System.out.println(member.toString());
		System.out.println("*******************************");
		return memberService.registMember(member);
	}
}
