package com.mage.park.login.service;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mage.park.common.consts.ParkConsts;
import com.mage.park.user.bo.User;
import com.mage.platform.framework.context.ThreadContextHolder;
import com.mage.platform.utils.EncryptUtils;
import com.mage.platform.utils.SqlUtils;

@Service
public class LoginService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	public String login(String userCode, String userPass, String checkCode){
		String errorMsg = "";
		try{
			// 登录校验
			HttpSession session = ThreadContextHolder.getHttpRequest().getSession();
			String checkmard = (String)session.getAttribute("checkmard");
			if(StringUtils.isEmpty(checkCode) || !checkCode.equalsIgnoreCase(checkmard))
				return "验证码错误";
			String decryptPass = EncryptUtils.encrypt(userPass, EncryptUtils.MD5);
			String sql = "SELECT * FROM sys_user a WHERE a.USER_CODE = ? AND a.USER_PASS = ?";
			User user = (User) SqlUtils.getExecutor().queryForObject(sql, User.class, userCode, decryptPass);
			if(null == user)
				return "用户名密码错误";
			session.setAttribute(ParkConsts.USER_SESSION, user);
		}catch(Exception e){
			errorMsg = "系统异常";
			logger.error(e.getMessage());
		}
		return errorMsg;
	}
	
}
