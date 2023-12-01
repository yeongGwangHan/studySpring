package com.itwillbs.persistence;

import com.itwillbs.domain.MemberVO;

public interface MemberDAO {
	
	// 추상메서드로 처리 동작 구현선언
	
	// 디비의 시간정보 조회
	public String getTime();
	
	// 회원가입 처리동작
	public void insertMember(MemberVO vo);
	
	// 로그인 처리동작
	public MemberVO loginMember(MemberVO vo); 
	public MemberVO loginMember(String userid,String userpw);
	// 객체로 보내는 이유 : 편함, 변경될 것들을 생각(문제될 것들을 생각)하면 수정 사항 없어도됨  
	
}
