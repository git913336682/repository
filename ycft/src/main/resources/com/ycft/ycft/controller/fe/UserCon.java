package com.ycft.ycft.controller.fe;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ycft.ycft.po.User;
import com.ycft.ycft.services.fe.UserSer;

@Controller(value="FEUserCon")
public class UserCon {
	
	@Autowired
	private UserSer us;
	
	/**
	 * �ͻ��˵�¼
	 * 
	 * @param u
	 * @param res
	 */
	@RequestMapping("login.do")
	public  void login(User u , HttpServletResponse res) {
		//�Ƿ��¼�ɹ�
		boolean isLogin = us.login(u.getSno(), u.getPsd());
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print("isSuccess="+isLogin);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			out.close();
		}
	}

}
