package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;

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
	
}
