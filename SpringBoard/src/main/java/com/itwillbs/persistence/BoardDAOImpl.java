package com.itwillbs.persistence;

import java.util.List;

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
	public void insertBoard(BoardVO vo) throws Exception{
		logger.debug("DAO - insertBoard(BoardVO vo) 호출");
		logger.debug("DAO 전달받은 vo :" +vo);
		sqlSession.selectOne(NAMESPACE+".insertBoard",vo);
		
		logger.debug("DAO vo :" +vo);
		logger.debug("DAO - insertBoard() 완료");
		
	}

	@Override
	public List<BoardVO> getBoardListAll() throws Exception {
		logger.debug("DAO - getBoardListAll() 호출");
		
		return sqlSession.selectList(NAMESPACE+".listAll");
	}

	@Override
	public BoardVO getBoard(int bno) throws Exception {
		logger.debug("DAO - getBoard(int bno)");
		
		return sqlSession.selectOne(NAMESPACE+".getBoard", bno);
	}

	@Override
	public void updateBoard(BoardVO vo) throws Exception {
		logger.debug("DAO - updateBoard() ");
		
		sqlSession.update(NAMESPACE+".updateBoard", vo);
	}
	
	
	
	
	

}
