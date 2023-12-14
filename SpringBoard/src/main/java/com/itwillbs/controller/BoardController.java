package com.itwillbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

@Controller
@RequestMapping(value="/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService mService;
	
	// 글쓰기 - GET
	@RequestMapping(value="/regist", method =RequestMethod.GET )
	public void registGET() throws Exception{
		logger.debug("/board/regist -> registGET() 호출");
		logger.debug("/board/regist.jsp 뷰페이지 이동");
	}
	
	// 글쓰기 - POST
	@RequestMapping(value="/regist", method =RequestMethod.POST )
	public String registPOST(BoardVO vo) throws Exception{
		logger.debug("폼 submit -> registPOST() 호출");
		// 한글 인코딩(필터)
		// 전달 정보저장
		logger.debug("vo : "+vo);
		
		// 서비스 - DB에 글쓰기(insert) 동작 호출
		mService.boardWrite(vo);
		
		
		logger.debug("/board/listAll 이동");
		return "/board/listAll";
	}
	
}
