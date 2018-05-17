package com.ycft.ycft.mapper;

import java.util.List;

import com.ycft.ycft.po.User;

public interface UserMapper {
	
	public List<User> feLogin(String stuCode);
	
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}