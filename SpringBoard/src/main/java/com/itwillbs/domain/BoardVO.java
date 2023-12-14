package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardVO {
	
	private Integer bno; // 데이터 자체를 표현할 때는 wrapper
	private String title;
	private String content;
	private String writer;
	private Timestamp regdate;
	private int viewcnt; // 연산 시 
}
