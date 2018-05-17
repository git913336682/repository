package com.ycft.ycft.controller.backstage;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ycft.ycft.po.User;
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
	/**
	 * 导出excel
	 * 
	 * @param student
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/exportExcel.action")
	public ModelAndView exportExcel(User user,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		System.out.println("------------------");
		boolean b = us.exportExcel(user,response);
		mav.setViewName("useritionMan/useritionSel/result.jsp");
		return mav;
	}
	
	/**
	 * 下载导入excel模版demo文件
	 * @param response
	 * @param request
	 */
	@RequestMapping("/downloadDemo.action")
	public void downloadDemo(HttpServletResponse response,HttpServletRequest request){
		response.reset();
        us.downloadDemo(response, request);
	}
	/**
	 * 导入excel
	 * @return
	 */
	@RequestMapping("/importExcel.action")
	public ModelAndView importExcel(MultipartFile file){
		ModelAndView mav = new ModelAndView();
		System.out.println("==========================");
		boolean b = false;
		try {
			b =  us.importExcel(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(b){
			mav.setViewName("success.jsp");
			
		}else{
			mav.setViewName("fail.jsp");
		}
		return mav;
	}
	
}
