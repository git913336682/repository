package com.ycft.ycft.services.backstage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ycft.ycft.mapper.UserMapper;

@Service(value="BSUserSer")
public class UserSer {

	@Autowired
	private UserMapper um;
	
	public boolean userAjax(String sno,String psd) {
		boolean flag = false;
		psd = md5Password(psd);
		
		if (psd.equals("")) {
			flag = true;
		}
		
		return flag;
	}
	
	/**
	 * md5加密算法
	 * 
	 * @author ZHENGBIN
	 */
	public static String md5Password(String psd) {
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(psd.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            // 标准的md5加密后的结果
            return buffer.toString();
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

	   }
	
	
}
