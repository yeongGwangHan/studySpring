package com.itwillbs.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

// @Service : 스프링(root-context.xml)에서 해당 객체를 서비스로 인식
//            => 서비스 객체(빈)으로 인식

@Service
public class MemberServiceImpl implements MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Inject
	private MemberDAO mdao;
	
	//
	@Override
	public void memberJoin(MemberVO vo) {
		// 컨트롤러가 전달해준 정보를 가지고 DAO를 처리
		// DAO 객체 생성 - 회원가입 처리 메서드 호출
		logger.debug("DAO 회원가입 메서드 호출 - 시작");
		mdao.insertMember(vo);
		logger.debug("DAO 회원가입 메서드 호출 - 끝");
		
	}

	@Override
	public MemberVO memberLogin(MemberVO vo) {
		logger.debug("Service - 로그인 처리 memberLogin(MemberVO vo)");
		
		return mdao.selectLoginMember(vo);
	}
	
	
	
	
	
}
