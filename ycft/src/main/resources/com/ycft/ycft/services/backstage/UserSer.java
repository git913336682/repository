package com.ycft.ycft.services.backstage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycft.ycft.mapper.UserMapper;
import com.ycft.ycft.tools.MD5;

@Service(value="BSUserSer")
public class UserSer {

	@Autowired
	private UserMapper um;
	
	public boolean userAjax(String sno,String psd) {
		boolean flag = false;
		psd = MD5.md5Password(psd);
		if (um.bsLogin(sno).getPsd().equals(psd)) {
			flag = true;
		}
		return flag;
	}
	
	
	
	
}
