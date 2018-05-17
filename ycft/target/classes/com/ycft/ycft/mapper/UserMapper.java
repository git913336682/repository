package com.ycft.ycft.mapper;

<<<<<<< HEAD
import com.ycft.ycft.po.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
=======
import java.util.List;

import com.ycft.ycft.po.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    /*===============客户端方法================*/
    public List<User> feLogin(String stuCode , String pwd);
    
    /*===============客户端方法================*/
    
    /*===============服务端方法================*/
    //通过账号查
    public User bsLogin(String sno);
    List<User> selective(User us);
    public int batchInsert(List<User> uList);
    //全查
    public List<User> selAll();
    /*===============服务端方法================*/
>>>>>>> branch 'master' of https://github.com/git913336682/repository.git
}