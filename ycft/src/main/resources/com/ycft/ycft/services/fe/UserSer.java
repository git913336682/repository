package com.ycft.ycft.services.fe;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycft.ycft.mapper.UserMapper;
import com.ycft.ycft.po.User;

@Service(value="feUserSer")
public class UserSer {
	
	@Autowired
	private UserMapper um;
	
	
	public boolean login(String stuCode , String pwd , HttpServletResponse res) {
		//登录
		List<User> list =  um.feLogin(stuCode,pwd);
		if(list != null && list.size() >  0) {
			User u = list.get(0);
			//去个空格吧
			pwd = pwd.trim();
			if(u.getPsd().equals(pwd)) {
				//开始存储session,cookie啥的
				//实例化一个Cookie  
		        Cookie cookie1 = new Cookie("uid", "" + u.getId());  
		        Cookie cookie2 = new Cookie("username", u.getSname());  
		        //设置Cookie的生命期限五年  
		        cookie1.setMaxAge(5*365*24*60*60);  
		        //添加Cookie到客户端  
		        res.addCookie(cookie1);  
		        res.addCookie(cookie2); 
				//........../
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
}
