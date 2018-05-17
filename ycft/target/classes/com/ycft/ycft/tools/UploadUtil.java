package com.ycft.ycft.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {
    
    
    /**
     * �ϴ�ͼƬ��������
     * @param request
     * @param filedata
     * @return
     * @throws IOException
     */
    public static String commonUpload(HttpServletRequest request, MultipartFile filedata) throws IOException {
    	// ��ȡʱ��� �����Ի�ȡʱ����������ϴ����ļ���
        String fileName = TimeTool.getRandomOrderNoOrFileName();
        String path = request.getSession().getServletContext().getRealPath("/");
    	
    	byte[] by =  filedata.getBytes();
    	//ԭͼƬ����
    	String filename = filedata.getOriginalFilename();
    	//�ļ���׺ .jpg
    	String suffix = filename.substring(filename.lastIndexOf("."));
    	//���ļ���
    	String newFileName = System.currentTimeMillis() + suffix;
    	System.out.println(newFileName+"--------");
    	System.out.println(path);
    	//�ϴ�ͼƬ��������
    	FileOutputStream fos;
    	String finalPathAndName1 = path +"images/"+newFileName;
    	fos = new FileOutputStream(finalPathAndName1);
    	fos.write(by);
    	fos.close();
        return "http://localhost:8080/ycft/images/"+newFileName;
    }
    
}

