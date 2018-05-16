package com.ycft.ycft.controller.backstage;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ycft.ycft.services.backstage.UserSer;

@Controller(value="BSUserCon")
public class UserCon {
	
	@Autowired
	private UserSer us;
	
	
	@RequestMapping("/userAjax.do")
	public void UserAjax(HttpServletResponse response,HttpServletRequest request) {
		String sno = request.getParameter("sno");
		String psd = request.getParameter("psd");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			boolean flag = us.userAjax(sno, psd);
			if (flag) {
				writer.println("1");
			} else {
				writer.print("0");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			writer.flush();
			writer.close();
		}
		
	}
}
