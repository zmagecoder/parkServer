package com.mage.park.common.utils;

import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.mage.park.common.bo.PageInfo;
import com.mage.park.common.consts.ParkConsts;
import com.mage.park.common.service.CommonService;
import com.mage.park.user.bo.User;
import com.mage.platform.framework.context.SpringContextHolder;
import com.mage.platform.framework.context.ThreadContextHolder;

public class ParkUtils {
	
	/**
	 * 获取系统语言
	 * @author pzh
	 * @date 2017年2月9日 上午10:53:19
	 * @return
	 */
	public static String getLanguage(){
		String language = ParkConsts.SYSTEM_LANGUAGE_C;
		try{
			HttpSession session = ThreadContextHolder.getHttpRequest().getSession();
			language = (String)session.getAttribute(ParkConsts.LANGUAGE_PREX);
			if(StringUtils.isEmpty(language))
				language = ParkConsts.SYSTEM_LANGUAGE_C;
		}catch(Exception e){}
		return language;
	}
	
	/**
	 * 设置系统语言
	 * @author pzh
	 * @date 2017年2月9日 上午10:53:28
	 * @param language
	 */
	public static void setLanguage(String language){
		try{
			HttpSession session = ThreadContextHolder.getHttpRequest().getSession();
			session.setAttribute(ParkConsts.LANGUAGE_PREX, language);
		}catch(Exception e){}
	}
	
	/**
	 * 获取界面信息
	 * @author pzh
	 * @date 2017年2月9日 下午3:01:44
	 * @param pageCode
	 * @return
	 */
	public static PageInfo getPageInfo(String pageCode){
		PageInfo pageInfo = (PageInfo)ParkCache.getInstance().get(pageCode);
		if(null == pageInfo){				//缓存没有，查询后放入缓存
			CommonService commonService = SpringContextHolder.getBean("commonService");
			pageInfo = commonService.getPageInfo(pageCode);		//登录界面信息
			ParkCache.getInstance().set(pageCode, pageInfo);
		}
		return pageInfo;
	}
	
	/**
	 * 获取当前登录用户
	 * @author pzh
	 * @date 2017年2月9日 下午4:46:53
	 * @return
	 */
	public static User getCurrUser(){
		return (User)ThreadContextHolder.getHttpRequest().
									getSession().getAttribute(ParkConsts.USER_SESSION);
	}
	
}	
