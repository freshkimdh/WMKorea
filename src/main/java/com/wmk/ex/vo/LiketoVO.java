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
 	private int FBOARD_NUM;            //             NUMBER(4)    NOT NULL,
 	private String id;  		//              VARCHAR2(50)    NOT NULL,
 	private int like_check;     //         NUMBER(5)    DEFAULT 0 NULL,


 	
}