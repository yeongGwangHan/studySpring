package com.itwillbs.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	private static final String NAMESPACE="com.itwillbs.mapper.BoardMapper";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void insertBoard(BoardVO vo) {
		logger.debug("DAO - insertBoard(BoardVO vo) 호출");
		logger.debug("DAO 전달받은 vo :" +vo);
		sqlSession.selectOne(NAMESPACE+".insertBoard",vo);
		
		logger.debug("DAO vo :" +vo);
		logger.debug("DAO - insertBoard() 완료");
		
	}

}
