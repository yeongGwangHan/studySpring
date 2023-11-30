package com.itwillbs.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

// @Repository : 스프링이 해당파일이 MemberDAO 역할을 
//               수행하는 객체로 인식되게 하는 코드
@Repository
public class MemberDAOImpl implements MemberDAO {

	// 디비연결정보(자동연결, mapper 접근...) 처리하는 객체가 필요하다
	// => root-context.xml에서 생성되어있는 객체(빈)를 주입
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public String getTime() {
		// 디비연결
		// SQL 구문작성 -> mapper.XML파일
		// SQL 실행
		String time = sqlSession.selectOne("com.itwillbs.mapper.MemberMapper.getTime");
		
		System.out.println("SQL 실행 완료!");
		System.out.println("time : "+time);
		
		return time;
	}
	
}
