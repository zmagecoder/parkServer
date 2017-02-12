package com.mage.park.growth.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mage.park.common.bo.PageInfo;
import com.mage.park.common.consts.ParkConsts;
import com.mage.park.common.utils.ParkUtils;
import com.mage.park.growth.service.GrowthService;
import com.mage.park.home.service.HomeService;

@Controller
public class GrowthController {
	
	@Resource
	private HomeService homeService;
	
	@Resource
	private GrowthService growthService;
	
	/**
	 * 收益记录主界面
	 * @author pzh
	 * @date 2017年2月9日 下午5:12:38
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobile/growth/main")
	public String toMain(HttpServletRequest request, Model model){
		//获取主界面信息
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_GROWTH_MAIN);
		model.addAttribute("pageInfo", pageInfo);
		
		//获取主页功能
		List<PageInfo> list = homeService.getPageFunc(ParkConsts.PAGE_GROWTH_MAIN, pageInfo.getPage_id());
		model.addAttribute("main", list);
		return "park/mobile/growth/index";
	}
	
	/**
	 * 车模奖励记录
	 * @author pzh
	 * @date 2017年2月9日 下午5:15:34
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobile/growth/models")
	public String getModels(HttpServletRequest request, Model model){
		//获取主界面信息
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_MODEL_LIST);
		model.addAttribute("pageInfo", pageInfo);
		List<Map<String, Object>> list = growthService.getModels();
		model.addAttribute("models", list);
		return "park/mobile/growth/models";
	}
	
	/**
	 * 车辆保养记录
	 * @author pzh
	 * @date 2017年2月9日 下午5:34:15
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobile/growth/maintain")
	public String getMaintainList(HttpServletRequest request, Model model){
		//获取主界面信息
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_MAINTAIN_LIST);
		model.addAttribute("pageInfo", pageInfo);
		List<Map<String, Object>> list = growthService.getMaintainList();
		model.addAttribute("maintainList", list);
		return "park/mobile/growth/byList";
	}
	
	/**
	 * 洗车记录
	 * @author pzh
	 * @date 2017年2月9日 下午5:40:39
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobile/growth/wash")
	public String getsList(HttpServletRequest request, Model model){
		//获取主界面信息
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_WASH_LIST);
		model.addAttribute("pageInfo", pageInfo);
		List<Map<String, Object>> list = growthService.getWashList();
		model.addAttribute("washList", list);
		return "park/mobile/growth/xcList";
	}
	
	/**
	 * 购车记录
	 * @author pzh
	 * @date 2017年2月10日 下午5:11:23
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobile/growth/buyCar")
	public String buyCarList(HttpServletRequest request, Model model){
		//获取主界面信息
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_BUY_CAR_LIST);
		model.addAttribute("pageInfo", pageInfo);
		List<Map<String, Object>> list = growthService.buyCarList();
		model.addAttribute("buyList", list);
		return "park/mobile/growth/buyCarList";
	}
}
