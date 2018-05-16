package com.ycft.ycft.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.ycft.ycft.tools.UploadUtil;

@Controller
public class Test {
	
	/**
	 * ʹ��summernote����ϴ�ͼƬ
	 * @param request
	 * @param response
	 * @param file �ļ���
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("upload.do")
	public void summernoteImage(HttpServletRequest request, HttpServletResponse response,MultipartFile file) throws Exception{
     try {
    	//ͼƬ�ϴ�֮�󷵻�ͼƬ��·��
    	 String path = UploadUtil.commonUpload(request, file);
    	 //����json���͵�����
    	 JSONObject jObject=new JSONObject();
    	 jObject.put("path", path);
    	 PrintWriter out=response.getWriter();
    	 out.print(jObject);
    	 out.close();
     }catch(Exception e) {
    	 e.printStackTrace();
     }
	 
    }
	
}
