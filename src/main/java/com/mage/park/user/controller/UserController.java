package com.mage.park.user.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mage.park.common.bo.PageInfo;
import com.mage.park.common.consts.ParkConsts;
import com.mage.park.common.utils.ParkUtils;
import com.mage.park.home.service.HomeService;
import com.mage.park.user.bo.User;
import com.mage.park.user.service.UserService;
import com.mage.platform.model.AssembleJSON;
import com.mage.platform.utils.SqlUtils;

@Controller
public class UserController {
	
	@Resource
	private UserService userService;
	
	@Resource
	private HomeService homeService;
	
	/**
	 * 停车位主界面信息
	 * @author pzh
	 * @date 2017年2月9日 下午3:47:02
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobile/user/main")
	public String toMain(HttpServletRequest request, Model model){
		//获取主界面信息
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_USER_CENTER);
		model.addAttribute("pageInfo", pageInfo);
		
		//获取主页功能
		List<PageInfo> list = homeService.getPageFunc(ParkConsts.PAGE_USER_CENTER, pageInfo.getPage_id());
		model.addAttribute("main", list);
		return "park/mobile/user/main";
	}
	
	/**
	 * 修改用户信息界面
	 * @author pzh
	 * @date 2017年2月9日 下午7:12:41
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobile/user/modifyInfo")
	public String modifyUser(HttpServletRequest request, Model model){
		//获取主界面信息
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_MODIFY_USER);
		model.addAttribute("pageInfo", pageInfo);
		
		return "park/mobile/user/modifyInfo";
	}
	
	/**
	 * 修改密码界面
	 * @author pzh
	 * @date 2017年2月9日 下午7:12:56
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobile/user/modifyPass")
	public String modifyPass(HttpServletRequest request, Model model){
		//获取主界面信息
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_MODIFY_PASS);
		model.addAttribute("pageInfo", pageInfo);
		
		return "park/mobile/user/modifyPass";
	}
	
	/**
	 * 保存用户资料
	 * @author pzh
	 * @date 2017年2月9日 下午7:15:15
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mobile/user/saveInfo")
	public AssembleJSON saveUser(User user){
		try{
			SqlUtils.getExecutor().insert("sys_user", user); //插入数据
		}catch(Exception e){
			return AssembleJSON.FAILURE("资料修改失败");
		}
		return AssembleJSON.SUCCESS;
	}
	
	/**
	 * 保存用户密码
	 * @author pzh
	 * @date 2017年2月9日 下午7:15:29
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mobile/user/savePass")
	public AssembleJSON savePass(User user){
		try{
			
		}catch(Exception e){
			return AssembleJSON.FAILURE("密码修改失败");
		}
		return AssembleJSON.SUCCESS;
	}
	
}
