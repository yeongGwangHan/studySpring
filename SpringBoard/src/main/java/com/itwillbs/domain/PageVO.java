package com.itwillbs.domain;

/**
 * 
 * 	페이징 처리에 필요한 정보를 저장
 *     => 총 개수, 시작페이지번호, 끝페이지 번호, 이전링크, 다음링크, 블럭의 크기
 *		  +Cri(페이지번호, 페이지사이즈)
 *	
 *	총 개수 : totalCount (DB 조회)
 *	끝 페이지 : endPage = 올림(페이지번호/블럭의 크기)* 블럭의 크기
 *  시작 페이지  : startPage = (endPage - 블럭의 크기) + 1;
 *  이전 링크 : prev = startPage != 1
 *	다음 링크 : next(boolean) endPage * 페이지 사이즈 < totalCount
 *
 *	*끝 페이지 계산
 *	ex) 총 122개 / 페이지당 10개씩 출력 / 블럭크기 : 10(1,2,3,...,10)
 *     - 1페이지 : 시작페이지번호 : 1 끝페이지 번호 : 10 / 이전 : N 다음 : Y
 *     				  ceil(1/10) * 10 => 10
 *     - 7페이지 : 시작페이지번호 : 1 끝페이지 번호 : 10 / 이전 : N 다음 : Y
 *     - 12페이지 : 시작페이지번호 : 11 끝페이지 번호 : 20->13 / 이전 : Y 다음 : N
 *     				   ceil(12/10) * 10 => 20
 *
 */

public class PageVO {
	
	private int totalCount; // 총 개수
	private int startPage;  // 페이지 블럭 시작번호
	private int endPage; 	// 페이지 블럭 끝번호
	
	private boolean prev;	// 이전 링크
	private boolean next; 	// 다음 링크

	private int displayPageNum = 10; // 페이지 블럭의 크기
	
//	private int page;
//	private int pageSize;
	private Criteria cri;

	// => 페이지번호, 페이지 사이즈 저장
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		// 페이징 처리에 필요한 정보를 모두 계산
		calcDate();
	}
	
	private void calcDate() {
		// 페이징처리 정보를 모두 계산
		
		// 끝 페이지번호
		endPage = (int)(Math.ceil((cri.getPage()/(double)displayPageNum)))*displayPageNum;
		// 시작 페이지번호
		startPage = (endPage - displayPageNum) + 1;
		
		// 끝 페이지 번호체크 (글이 없는경우)
//		int tmpEndPage = totalCount / cri.getPageSize()+(totalCount%cri.getPageSize()==0? 0:1);
		int tmpEndPage = (int)(Math.ceil(((double)totalCount / cri.getPageSize())));
		
		if(endPage > tmpEndPage) {
			endPage = tmpEndPage;
		}
		
		// 이전 링크
		prev = startPage != 1;
//		prev = startPage == 1? false:true;
		// 다음 링크
		next = endPage * cri.getPageSize()<totalCount;
		
		// 페이징처리 정보를 계산완료
	} // calcData()
	
	
	
	

	public int getTotalCount() {
		return totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	@Override
	public String toString() {
		return "PageVO [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", cri=" + cri + "]";
	}
}
