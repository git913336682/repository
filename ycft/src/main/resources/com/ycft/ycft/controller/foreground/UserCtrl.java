package com.ycft.ycft.controller.foreground;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ycft.ycft.po.User;
import com.ycft.ycft.services.fe.UserSrv;

/**
 * �û����Ʋ�
 * @author ��˧
 *
 */
@Controller(value="foreUserCon")
@RequestMapping("fore/user/")
public class UserCtrl {
	
	@Autowired
	private UserSrv us;
	
	/**
	 * �ͻ��˵�¼
	 * 
	 * @author ��˧
	 * @param User u ������User����
	 * @param res HttpServletResponse����
	 * 2018��5��16������3:53:13
	 */ 
	@RequestMapping("login.do")
	public void login(User u , HttpServletResponse rspn) {
		//�Ƿ��¼�ɹ�
		User user = us.login(u.getSno(), u.getPsd());
		boolean isLogin = false;
		if (user != null) {
			//��ʼ�洢session,cookieɶ��
			//ʵ����һ��Cookie  
	        Cookie cookie1 = new Cookie("uid", String.valueOf(u.getId()));  
	        Cookie cookie2 = new Cookie("sno", u.getSno());  
	        //����Cookie��������������  
	        cookie1.setMaxAge(5*365*24*60*60);  
	        cookie2.setMaxAge(5*365*24*60*60);  
	        //���Cookie���ͻ���  
	        rspn.addCookie(cookie1);  
	        rspn.addCookie(cookie2); 
			//........../
	        isLogin = true;
		}
		PrintWriter out = null;
		try {
			out = rspn.getWriter();
			out.print("isSuccess="+isLogin);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

}
