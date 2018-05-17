package com.ycft.ycft.services.fe;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycft.ycft.mapper.UserMapper;
import com.ycft.ycft.po.User;
import com.ycft.ycft.tools.MD5;

@Service(value="feUserSer")
public class UserSer {
	
	@Autowired
	private UserMapper um;
	
	
	/**
	 * �û���¼
	 * 
	 * @author ��˧
	 * @param stuCode ѧ��
	 * @param pwd ����
	 * @param res HttpServletResponse����
	 * @return Boolean ��¼�Ƿ�ɹ�
	 * 2018��5��16������3:53:13
	 */
	public boolean login(String stuCode , String pwd , HttpServletResponse res) {
		//��¼
		List<User> list =  um.feLogin(stuCode,pwd);
		if(list != null && list.size() >  0) {
			User u = list.get(0);
			//ȥ���ո�
			pwd = pwd.trim();
			//�ȶ�Md5����
			if(u.getPsd().equals( MD5.md5Password(pwd) ) ) {
				//��ʼ�洢session,cookieɶ��
				//ʵ����һ��Cookie  
		        Cookie cookie1 = new Cookie("uid", String.valueOf(u.getId()));  
		        Cookie cookie2 = new Cookie("sno", u.getSno());  
		        //����Cookie��������������  
		        cookie1.setMaxAge(5*365*24*60*60);  
		        cookie2.setMaxAge(5*365*24*60*60);  
		        //���Cookie���ͻ���  
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
