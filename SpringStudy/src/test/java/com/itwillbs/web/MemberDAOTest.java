package com.itwillbs.web;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class MemberDAOTest {
	// 테스트 전용 클래스
	
	// 로거 객체 생성(출력전용 객체)
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOTest.class);
	
	
	// MemberDAO 객체가 필요 => memberDAOImpl 객체가 주입(DI)
	@Inject
	private MemberDAO mdao;
	
	// @Test 테스트 실행을 위한 필 수 어노테이션!!
	@Test
	public void mybatis_첫쿼리구문실행_테스트() {
		System.out.println("결과 : "+mdao.getTime());
		logger.info("결과 : "+mdao.getTime());
	}
	
	
	
}
