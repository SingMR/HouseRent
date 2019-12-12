package com.house.wym.dao;


import com.house.wym.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {
	/**
	 * 用户登录
	 * 
	 * @return
	 */
	public Users login(Users user);

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	public int regist(Users user);
	/**
	 * 修改密码
	 * @return
	 */
	public int updateUserPwd(Users users);
	/**
	 * 检查旧密码
	 * @return
	 */
	public Users checkOldPwd(Users users);
}
