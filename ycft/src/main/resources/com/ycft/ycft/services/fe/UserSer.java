package com.ycft.ycft.services.fe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ycft.ycft.mapper.UserMapper;
import com.ycft.ycft.po.User;

@Service(value="FEUserSer")
public class UserSer {
	
	@Autowired
	private UserMapper um;
	
	
	public boolean login(String stuCode , String pwd) {
		//µÇÂ¼
		List<User> list =  um.feLogin(stuCode,pwd);
		if(list != null && list.size() >  0) {
			User u = list.get(0);
			//È¥¸ö¿Õ¸ñ°É
			pwd = pwd.trim();
			if(u.getPsd().equals(pwd)) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
}
