package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Inject
	private BoardDAO bdao;

	@Override
	public void boardWrite(BoardVO vo) throws Exception {
		logger.debug("service - insertBoard(BoardVO vo) 호출");
		logger.debug("servie 전달받은 vo :" +vo);
		
		bdao.insertBoard(vo);
		
		logger.debug("servie vo :" +vo);
		logger.debug("sevice - insertBoard 완료");
	}

	@Override
	public List<BoardVO> boardListAll() throws Exception {
		logger.debug("service - boardListAll() 호출");
		
		return bdao.getBoardListAll();
	}

	@Override
	public BoardVO getBoard(int bno) throws Exception {
		logger.debug("service - getBoard(int bno)");
		
		return bdao.getBoard(bno);
	}

	@Override
	public void updateBoard(BoardVO vo) throws Exception {
		logger.debug("service - getBoard(int bno)");
		
		bdao.updateBoard(vo);
	}
	
	
	
}
