package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

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
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.PageVO;
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
		
		rttr.addFlashAttribute("result", "CREATEOK"); // redirect 할때만 사용가능
		
		logger.debug("/board/listAll 이동");
		return "redirect:/board/listAll"; // redirect 이유 새로고침 할 때 데이터 중복 작성됨
	}
	
	// 게시판 리스트 - GET
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public String listAllGET(Model model, 
							 @ModelAttribute("result") String result,
							 HttpSession session
							 ) throws Exception {
		logger.debug("/board/listAll -> listAllGET()");
		
		session.setAttribute("viewcntCheck", true);
		
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
	public void readGET(@RequestParam("bno") int bno, Model model,HttpSession session) throws Exception{ // 1:1 일 경우에는 RequestParam을 쓰는 것이 더 낫다.
		logger.debug("/board/read -> readGET()");
		
		// 전달정보 저장
		logger.debug("bno : "+bno);
		
		if((boolean) session.getAttribute("viewcntCheck")) {
			// 서비스 - bno에 해당하는 글 조회수 1증가
			//		   (페이지 호출당 1번씩 증가/read페이지 새로고침시 증가X)
			bService.increaseViewCnt(bno);
			
			session.setAttribute("viewcntCheck", false);
		}
		
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
		logger.debug("수정할 글번호 : "+bno);

		// 서비스 - bno에 해당하는 특정 글정보만 조회
		BoardVO resultVO = bService.getBoard(bno);
		// 연결된 뷰페이지로 이동시 정보를 전달
		model.addAttribute("resultVO", resultVO);
	}
	
	// 게시판 글 수정 - POST
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO vo, RedirectAttributes rttr) throws Exception{
		logger.debug("/board/modify -> modifyPOST()");
		
		// 전달 정보 저장
		logger.debug("수정할 정보 : "+vo);
		
		// 서비스 - bno에 해당하는 특정 글정보만 수정
		int result = bService.updateBoard(vo);
		
		// 처리 완료후뷰페이지 이동(리스트 이동)
		// + 수정 완료! 리스트에서 출력
		rttr.addFlashAttribute("result", "modifyOK");
		
		return "redirect:/board/listAll";
	}
	
	// 게시판 글 삭제 - POST
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String deletePOST(int bno, RedirectAttributes rttr) throws Exception{
		logger.debug("/board form -> deletePOST");
		
		// 서비스 - bno에 해당하는 특정 글정보만 삭제
		bService.deleteBoard(bno);
		
		// "글 삭제 완료!" 메세지 출력
		rttr.addFlashAttribute("result", "deleteOK");
		
		return "redirect:/board/listAll";
	}
	
	/**
	 *  페이징처리
	 *  0. 반드시 GET방식으로만 처리!
	 *  1. 원하는 만큼의 데이터를 가져와서 출력
	 *  2. 페이지 블럭(하단 블럭) 생성
	 *  3. 본문/수정/삭제등 ... 처리후 리스트 이동시 기존의 정보를 유지
	 *     
	 *  a태그 : 네이버 쇼핑 / 유사한 코드의 반복적인 동작 수행
	 *  	    => 검색엔진 노출이 쉬움
	 *  
	 *  form태그 : 쿠팡 / input태그를 사용해서 처리
	 *    		=> 데이터처리 (빠른 처리)
	 *    
	 *  2. 하단 페이징 블럭
	 *    1) 시작페이지 번호
	 *    2) 끝 페이지 번호
	 *    3) 전체 데이터(글)의 개수
	 *    4) 이전페이지 링크(boolean)
	 *    5) 다음페이지 링크(boolean)
	 *    
	 *    ex) 총 122개 / 페이지당 10개씩 출력
	 *     - 1페이지 : 시작페이지번호 : 1 끝페이지 번호 : 10 / 이전 : N 다음 : Y
	 *     - 7페이지 : 시작페이지번호 : 1 끝페이지 번호 : 10 / 이전 : N 다음 : Y
	 *     - 12페이지 : 시작페이지번호 : 11 끝페이지 번호 : 20->13 / 이전 : Y 다음 : N
	 *    
	 */
	
	// http://localhost:8088/board/listPage
	// http://localhost:8088/board/listPage?page=1
	// http://localhost:8088/board/listPage?pageSize=20
	// http://localhost:8088/board/listPage?page=3&pageSize=15
	// 게시판 리스트 - GET
		@RequestMapping(value = "/listPage", method = RequestMethod.GET)
		public String listPageGET(Model model, 
								 @ModelAttribute("result") String result,
								 HttpSession session,
								 Criteria cri
								 ) throws Exception {
			logger.debug("/board/listPage -> listPageGET()");
			
			session.setAttribute("viewcntCheck", true);
			
//			Criteria cri = new Criteria();
//			cri.setPage(2);
//			cri.setPageSize(10);
			
			// 서비스 - 디비에 저장된 글을 가져오기
			List<BoardVO> boardList = bService.boardListPage(cri);
//			logger.debug("@_@"+boardList);
			
			// 페이지 블럭 정보 준비 -> view 페이지 전달
			PageVO pageVO = new PageVO();
			pageVO.setCri(cri);
			pageVO.setTotalCount(646); // 디비에서 직접 실행결과 가져오기
			
			logger.debug("확인 : "+pageVO);
			model.addAttribute("pageVO", pageVO);
			// 데이터를 연결된 뷰페이지로 전달(Model)
			model.addAttribute("boardList", boardList); // 배열일 경우 이름 지정해놓는 것이 좋음(직관적으로 알아보기 위해서)
//			model.addAttribute("boardList", bService.boardListAll());
			
			return "/board/listAll";
		}
	
	
	
} // controller
