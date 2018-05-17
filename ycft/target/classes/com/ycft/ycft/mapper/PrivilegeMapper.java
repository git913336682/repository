package com.ycft.ycft.mapper;

<<<<<<< HEAD
import com.ycft.ycft.po.Privilege;

public interface PrivilegeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Privilege record);

    int insertSelective(Privilege record);

    Privilege selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Privilege record);

    int updateByPrimaryKey(Privilege record);
=======
import java.util.List;

import com.ycft.ycft.po.Privilege;
import com.ycft.ycft.po.User;

public interface PrivilegeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Privilege record);

    int insertSelective(Privilege record);

    Privilege selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Privilege record);

    int updateByPrimaryKey(Privilege record);
    
    List<Privilege> queryMenuList(User user);
>>>>>>> branch 'master' of https://github.com/git913336682/repository.git
}