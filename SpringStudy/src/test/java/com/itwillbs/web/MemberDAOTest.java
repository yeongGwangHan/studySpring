package com.itwillbs.web;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class MemberDAOTest {
	// 테스트 전용 클래스
	
	// 로거 객체 생성(출력전용 객체)
	// mylogger 단축어
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOTest.class);
	
	// MemberDAO 객체가 필요 => memberDAOImpl 객체가 주입(DI)
	@Inject
	private MemberDAO mdao;
	
	// @Test 테스트 실행을 위한 필 수 어노테이션!!
//	@Test
	public void mybatis_첫쿼리구문실행_테스트() {
		System.out.println("결과 : "+mdao.getTime());
		logger.info("결과 : "+mdao.getTime());
		logger.info("@@@@@@@@@@@@@@@@@@@@@@@");	// li 단축어
		logger.debug("!!!!!!!!!!!!!!!!!!!!!!");	// ld 단축어
	}
	
//	@Test
	public void 회원가입_테스트() {
		logger.debug("회원가입_테스트() - 시작");
		
		// 임시회원정보
		MemberVO vo = new MemberVO();
		vo.setUserid("admin2");
		vo.setUserpw("1234");
		vo.setUsername("관리자");
		vo.setUseremail("admin@admin.com");
		
		mdao.insertMember(vo);
		
		logger.debug("회원가입_테스트() - 끝");
	}
	
//	@Test
	public void 로그인_테스트() {
		logger.debug("로그인_테스트()");
		
		MemberVO vo = new MemberVO();
		vo.setUserid("admin1");
		vo.setUserpw("1234");
		
//		MemberVO resultVo = mdao.loginMember(vo);
		MemberVO resultVo = mdao.loginMember("admin","1234");
		logger.debug("결과 : "+resultVo);
		
		if(resultVo != null) {
			logger.debug("☆*: .｡. o(≧▽≦)o .｡.:*☆ 로그인 성공!!!");
		}else {
			logger.debug("╚(•⌂•)╝로그인 실패!!!");
		}
	}
	
	@Test
	public void 회원정보조회_테스트() {
		logger.debug("사용자의 ID사용해서 회원정보 모두 조회");
		
		
	}
}
