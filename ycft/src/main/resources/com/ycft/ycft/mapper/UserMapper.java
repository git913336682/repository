package com.ycft.ycft.mapper;

import java.util.List;

import com.ycft.ycft.po.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    /*===============�ͻ��˷���================*/
    public List<User> feLogin(String stuCode , String pwd);
    
    /*===============�ͻ��˷���================*/
}