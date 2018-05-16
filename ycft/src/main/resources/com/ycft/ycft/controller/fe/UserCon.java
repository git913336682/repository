package com.ycft.ycft.controller.fe;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ycft.ycft.po.User;
import com.ycft.ycft.services.fe.UserSer;

/**
 * 用户控制层
 * @author 邵帅
 *
 */
@Controller(value="feUserCon")
@RequestMapping("fe/userCon/")
public class UserCon {
	
	@Autowired
	private UserSer us;
	
	/**
	 * 客户端登录
	 * 
	 * @author 邵帅
	 * @param User u 参数绑定User对象
	 * @param res HttpServletResponse对象
	 * 2018年5月16日下午3:53:13
	 */ 
	@RequestMapping("login.do")
	public  void login(User u , HttpServletResponse res) {
		//是否登录成功
		boolean isLogin = us.login(u.getSno(), u.getPsd() , res);
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
