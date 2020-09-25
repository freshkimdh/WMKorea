package com.wmk.ex.mapper;

import org.apache.ibatis.annotations.Delete;
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
	
	 //È¸¿ø Å»Åð 
	@Delete("delete from wmk_authorities where id = #{id}")
	public void authori(String id);
	@Delete("delete from wmk_users where id = #{id} and pw = #{pw}")
	public void delMember(UserVO userVO);
	}
	
