package com.itwillbs.persistence;

import com.itwillbs.domain.BoardVO;

public interface BoardDAO {
	
	// 글쓰기
	public void insertBoard(BoardVO vo);
}
