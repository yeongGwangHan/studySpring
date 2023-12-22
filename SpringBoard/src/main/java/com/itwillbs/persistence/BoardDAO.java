package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

public interface BoardDAO {
	
	// 글쓰기
	public void insertBoard(BoardVO vo) throws Exception;
	
	// 글 정보 전부 가져오기
	public List<BoardVO> getBoardListAll() throws Exception;
	
	// 특정 글 정보 가져오기
	public BoardVO getBoard(int bno) throws Exception;
	
	// 특정 글 정보 수정
	public int updateBoard(BoardVO vo) throws Exception;
	
	// 특정 게시판글 조회수 증가
	public void updateViewCnt(int bno) throws Exception;
	
	// 특정 게시판글 삭제
	public void deleteBoard(int bno) throws Exception;
	
	// 글 목록 조회(page)
	public List<BoardVO> getBoarListPage(int page) throws Exception;
	// 글 목록 조회(분류 객체)
	public List<BoardVO> getBoarListPage(Criteria cri) throws Exception;
	
	// 글 갯수
	public int getBoardCount() throws Exception;
	
}
