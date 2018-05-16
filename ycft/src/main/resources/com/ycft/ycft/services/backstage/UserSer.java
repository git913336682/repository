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
	 * md5�����㷨
	 * 
	 * @author ZHENGBIN
	 */
	public static String md5Password(String psd) {
        try {
            // �õ�һ����ϢժҪ��
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(psd.getBytes());
            StringBuffer buffer = new StringBuffer();
            // ��ÿһ��byte ��һ�������� 0xff;
            for (byte b : result) {
                // ������
                int number = b & 0xff;// ����
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            // ��׼��md5���ܺ�Ľ��
            return buffer.toString();
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

	   }
	
	
}
