package com.itwillbs.service;

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
	public void boardWrite(BoardVO vo) {
		logger.debug("service - insertBoard(BoardVO vo) 호출");
		logger.debug("servie 전달받은 vo :" +vo);
		
		bdao.insertBoard(vo);
		
		logger.debug("servie vo :" +vo);
		logger.debug("sevice - insertBoard 완료");
		
	}

}
