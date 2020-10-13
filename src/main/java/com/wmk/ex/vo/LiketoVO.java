package com.wmk.ex.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;
@Log4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LiketoVO {

	 	private int likeNo;       	//               NUMBER(5)    NOT NULL PRIMARY KEY,
	 	private int FBOARD_NUM;            //             NUMBER(4)    NOT NULL,
	 	private String id;  		//              VARCHAR2(50)    NOT NULL,
	 	private int like_check;     //         NUMBER(5)    DEFAULT 0 NULL,
}
