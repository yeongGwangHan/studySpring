package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardService {

	// 글쓰기
	public void boardWrite(BoardVO vo) throws Exception;
	
	// 게시판글 정보 가져오기
	public List<BoardVO> boardListAll() throws Exception;
	
	// 특정 게시판글 정보 가져오기
	public BoardVO getBoard(int bno) throws Exception;
	
	// 특정 게시판글 수정
	public int updateBoard(BoardVO vo) throws Exception;
	
	// 특정 게시판글 조회수 증가
	public void increaseViewCnt(int bno) throws Exception;
	
	// 특정 게시판글 삭제
	public void deleteBoard(int bno) throws Exception;
}