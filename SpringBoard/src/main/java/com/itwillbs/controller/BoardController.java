package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

@Controller
@RequestMapping(value="/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService bService;
	
	// http://localhost:8088/board/regist
	// 글쓰기 - GET
	@RequestMapping(value="/regist", method =RequestMethod.GET )
	public void registGET() throws Exception{
		logger.debug("/board/regist -> registGET() 호출");
		logger.debug("/board/regist.jsp 뷰페이지 이동");
	}
	
	// 글쓰기 - POST
	@RequestMapping(value="/regist", method =RequestMethod.POST )
	public String registPOST(BoardVO vo, RedirectAttributes rttr) throws Exception{
		logger.debug("폼 submit -> registPOST() 호출");
		// 한글 인코딩(필터)
		// 전달 정보저장
		logger.debug("vo : "+vo);
		
		// 서비스 - DB에 글쓰기(insert) 동작 호출
		bService.boardWrite(vo);
		logger.debug("글작성 완료!");
		
		rttr.addFlashAttribute("result", "CREATEOK"); 
		
		logger.debug("/board/listAll 이동");
		return "redirect:/board/listAll"; // redirect 이유 새로고침 할 때 데이터 중복 작성됨
	}
	
	// 게시판 리스트 - GET
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public String listAllGET(Model model, @ModelAttribute("result") String result) throws Exception {
		logger.debug("/board/listAll -> listAllGET()");
		
		// 서비스 - 디비에 저장된 글을 가져오기
		List<BoardVO> boardList = bService.boardListAll();
		logger.debug("@_@"+boardList);
		
		// 데이터를 연결된 뷰페이지로 전달(Model)
		model.addAttribute("boardList", boardList); // 배열일 경우 이름 지정해놓는 것이 좋음(직관적으로 알아보기 위해서)
//		model.addAttribute("boardList", bService.boardListAll());
		
		return "/board/listAll";
	}
	
	// 글 본문보기 - GET
	@GetMapping(value = "/read")
	public void readGET(@RequestParam("bno") int bno, Model model) throws Exception{ // 1:1 일 경우에는 RequestParam을 쓰는 것이 더 낫다.
		logger.debug("/board/read -> readGET()");
		
		// 전달정보 저장
		logger.debug("bno : "+bno);
		
		// 서비스 - bno에 해당하는 특정 글정보만 조회
		BoardVO resultVO = bService.getBoard(bno);
		// 연결된 뷰페이지로 이동시 정보를 전달
		model.addAttribute("resultVO", resultVO);
		// model.addAttribute("resultVO"); => "boardVO" 이름으로 호출

		// /board/read.jsp 뷰페이지로 이동
	}
	
	// 게시판 글 수정 - GET
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") int bno, Model model) throws Exception{
		logger.debug("/board/modify -> modifyGET()");
		
		// 전달정보 저장 
		logger.debug("bno : "+bno);

		// 서비스 - bno에 해당하는 특정 글정보만 조회
		BoardVO resultVO = bService.getBoard(bno);
		// 연결된 뷰페이지로 이동시 정보를 전달
		model.addAttribute("resultVO", resultVO);
	}
	
	// 게시판 글 수정 - POST
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO vo) throws Exception{
		logger.debug("/board/modify -> modifyGET()");
		
		// 전달 정보 저장
		logger.debug("수정할 정보 : "+vo);
		
		// 서비스 - bno에 해당하는 특정 글정보만 수정
//		bService.updateBoard(vo);
		
		// 뷰페이지 이동
		
		return "redirect:/board/listAll";
	}
}
