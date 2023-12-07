package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;

@Controller
//@RequestMapping(value = "/members/*") // 컨트롤러를 구분하는 주소 매핑 ~.me ~.bo
@RequestMapping(value = "/members/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	// http://localhost:8088/controller/members/join
	// http://localhost:8088/controller/join
	// http://localhost:8088/members/join
	// 회원가입(정보입력)
	@RequestMapping(value = "/join",method = RequestMethod.GET) // GET : 조회의 의미, 입력/출력 할 때
	public void memberJoinGET() {
		logger.debug(" /members/join 호출 -> memberJoinGET() 실행 ");
		// 연결된 뷰페이지로 이동
		logger.debug("/views/members/join.jsp페이지로 이동");
		
	}
	// 회원가입(정보처리)
	@RequestMapping(value = "/join",method = RequestMethod.POST) // POST : 처리의 의미(프로세스)
	public String memberJoinPOST(MemberVO vo) { // @ModelAttribute 생략
		logger.debug("memberJoinPOST 호출");
		// 한글처리(인코딩 설정)
		// 전달정보 저장
		logger.debug("vo : "+vo);
		
		// DB에 정보를 저장
		// 페이지 이동 (로그인페이지-/members/login)
		return "redirect:/members/login";
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public void memberLoginGET() {
		logger.debug("/members/login 호출 -> memberLoginGet() 실행");
	}
	
	
}
