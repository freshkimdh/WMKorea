package com.wmk.ex.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LiketoVO {

 	private int likeNo;       	//               NUMBER(5)    NOT NULL PRIMARY KEY,
 	private int rBoardNum;            //             NUMBER(4)    NOT NULL,
 	private String id;  		//              VARCHAR2(50)    NOT NULL,
 	private int like_check;     //         NUMBER(5)    DEFAULT 0 NULL,

 	// 현재 로직에서는 사용하지는 않지만 
 	// 로직을 변경할시 사용 할수있음 
 	// 좋아요 누른 게시판 번호와  좋아요를 누른 회원의 아이디가 같이 확인을 할수있음.
 	
}