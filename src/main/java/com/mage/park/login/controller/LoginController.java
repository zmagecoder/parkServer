package com.mage.park.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mage.park.common.bo.PageInfo;
import com.mage.park.common.consts.ParkConsts;
import com.mage.park.common.service.CheckMard;
import com.mage.park.common.service.CommonService;
import com.mage.park.common.utils.ParkUtils;
import com.mage.park.login.service.LoginService;
import com.mage.platform.model.AssembleJSON;

@Controller
public class LoginController {
	
	@Resource
	private LoginService loginService;
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private CheckMard checkMard;
	
	/**
	 * 绘制登录界面
	 * @author pzh
	 * @date 2017年2月9日 上午11:45:03
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/")
	public String toIndex(HttpServletRequest request, Model model){
		String deviceType = (String)request.getSession().getAttribute("ua");
		if(ParkConsts.CLIENT_DEVICE_MOBILE.equals(deviceType)){
			this.mobileLogin(model);
			return "park/mobile/login/login";
		}else {
			this.pcLogin(model);
			return "park/pc/login/login";
		}
	}
	
	/**
	 * 用户登录
	 * @author pzh
	 * @date 2017年2月9日 下午1:25:01
	 * @param userCode
	 * @param userPass
	 * @param checkCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login")
	public AssembleJSON login(String userCode, String userPass, String checkCode){
		String errorMsg = loginService.login(userCode, userPass, checkCode);
		//如果有返回错误信息
		if(StringUtils.isEmpty(errorMsg))
			return AssembleJSON.SUCCESS;
		return AssembleJSON.FAILURE(errorMsg);
	}
	
	/**
	 * 切换系统语言
	 * @author pzh
	 * @date 2017年2月9日 下午1:29:44
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/switch/language")
	public String switchLanguage(String language){
		ParkUtils.setLanguage(language);
		return "redirect:/";
	}
	
	/**
	 * 获取验证码
	 * @author pzh
	 * @date 2017年2月9日 下午1:49:00
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getChekMard")
	public void getChekMard(HttpServletRequest request, HttpServletResponse response){
		checkMard.getChekMard(request, response);
	}
	
	/**
	 * 处理手机登录页
	 * @author pzh
	 * @date 2017年2月9日 下午2:39:34
	 * @param model
	 */
	private void mobileLogin(Model model){
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_LOGIN);
		model.addAttribute("pageInfo", pageInfo);
	}
	
	/**
	 * 处理电脑登录页
	 * @author pzh
	 * @date 2017年2月9日 下午2:42:18
	 * @param model
	 */
	private void pcLogin(Model model){
		//TODO 后续开发
	}
}