package com.wmk.ex.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wmk.ex.vo.UserVO;

@Mapper
public interface UserMapper {

	@Insert("insert into wmk_users(id,pw,nickname,email,nationality,enabled,login_Type)"
			+ "values(#{id},#{pw},#{nickname},#{email},#{nationality},#{enabled}, #{login_Type})")	
	public int insertUser(UserVO userVO);
	
	@Insert("insert into wmk_authorities (id, authority) values(#{id}, 'ROLE_USER')") 
	public void insertAuthorities(UserVO userVO);
	
	//kakao social
	@Select("select * from wmk_users where id = #{id}")
	public UserVO readUser(String id);
	@Select("select * from wmk_users where login_Type = #{login_Type}")
	public UserVO readUserLoginType(String login_Type);
	@Select("select * from wmk_users where id = #{id} and login_Type = #{login_Type}")
	public UserVO readUserByIdAndLoginType(@Param("id")String id,@Param("login_Type")String login_Type);
	
	
	
	//DeleteUsers
	@Delete("delete from wmk_authorities where id = #{id}")
	public void authori(String id);
	@Delete("delete from wmk_users where id = #{id} and pw = #{pw}")
	public void delMember(UserVO userVO);
	
	//UpdateUsers
	@Update("update wmk_users set pw = #{pw}, nickname = #{nickname,jdbcType=VARCHAR}, email =#{email,jdbcType=VARCHAR}, nationality =#{nationality,jdbcType=VARCHAR}  where id = #{id}")
	public void modifyUser(UserVO userVO);
	//UserIdCheck
	@Select("select count(*) from wmk_users where id = #{id}")
	public int idChk(String id);
	}
	
