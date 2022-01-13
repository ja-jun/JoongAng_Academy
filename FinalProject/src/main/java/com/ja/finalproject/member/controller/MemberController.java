package com.ja.finalproject.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ja.finalproject.member.service.MemberService;
import com.ja.finalproject.vo.MemberVo;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("loginPage")
	public String loginPage() {
		
		System.out.println("시스템 로그] 로그인 페이지 실행...");
		
		return "member/loginPage";
	}
	
	@RequestMapping("joinMemberPage")
	public String joinMemberPage() {
		
		System.out.println("시스템 로그] 로그인 페이지 실행...");
		
		return "member/joinMemberPage";
		
	}
	@RequestMapping("joinMemberProcess")
	public String joinMemberProcess(MemberVo param) {
		
		System.out.println("시스템 로그] 로그인 페이지 실행...");
		System.out.println("시스템 로그] 파라미터 값 id : " + param.getMember_id());
		System.out.println("시스템 로그] 파라미터 값 pw : " + param.getMember_pw());
		System.out.println("시스템 로그] 파라미터 값 nick : " + param.getMember_nick());
		System.out.println("시스템 로그] 파라미터 값 gender : " + param.getMember_gender());
		System.out.println("시스템 로그] 파라미터 값 birth : " + param.getMember_birth());
		System.out.println("시스템 로그] 파라미터 값 phone : " + param.getMember_phone());
		System.out.println("시스템 로그] 파라미터 값 email : " + param.getMember_email());
		System.out.println("시스템 로그] 파라미터 값 joindate : " + param.getMember_joindate());
		
		memberService.joinMember(param);
				
		return "member/joinMemberComplete";
	}
	
	@RequestMapping("loginProcess")
	public String loginProcess(MemberVo param, HttpSession session) {
		
		System.out.println("시스템 로그] 로그인 프로세스 실행...");
		
		MemberVo sessionUser = memberService.login(param); // MemberVo에 넣었다 엄청 중요
		
		if(sessionUser != null) {
			// 인증 성공
			// 여기 신경쓰자... session.setAttribute 딱 한번 활용됨... 앞으로 getAttribute 많이 쓴다...
			
			session.setAttribute("sessionUser", sessionUser);
			
			return "redirect:../board/mainPage"; // 
		} else {
			// 인증 실패
			return "member/loginFail";
		}
	}
	
	@RequestMapping("logoutProcess")
	public String logoutProcess(HttpSession session) {
		
		session.invalidate(); // 세션 저장 공간을 날리고 재구성...
	
		return "redirect:../board/mainPage"; // forwarding은 항상 jsp, redirect는 항상 링크
	}
	
}
