package com.wmk.ex.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.wmk.ex.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Mapper
@Qualifier("UserMapper")
public interface UserMapper {

	@Insert("insert into wmk_users(id,pw,nickname,email,nationality,enabled)"
			+ "values(#{id},#{pw},#{nickname},#{email},#{nationality},#{enabled})")	
	public int insertUser(UserVO userVO);
		
		
	
	 @Insert("insert into wmk_authorities (id, authority) values(#{id}, 'ROLE_USER')") 
	 public void insertAuthorities(UserVO userVO);
	
	
	 @Select("select * from wmk_users where id = #{id}")
	 public UserVO readUser(String id);
	 
	 
	}
	
