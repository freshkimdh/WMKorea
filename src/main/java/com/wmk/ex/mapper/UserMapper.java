package com.wmk.ex.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.wmk.ex.vo.UserVO;

@Mapper
public interface UserMapper {

	@Insert("insert into wmk_users(id,pw,nickname,email,nationality,enabled)"
			+ "values(#{id},#{pw},#{nickname},#{email},#{nationality},#{enabled})")	
	public int insertUser(UserVO userVO);
	
	@Insert("insert into wmk_authorities (id, authority) values(#{id}, 'ROLE_USER')") 
	public void insertAuthorities(UserVO userVO);
	
	@Select("select * from wmk_users where id = #{id}")
	public UserVO readUser(String id);
	  
	}
	
