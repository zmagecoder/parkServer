package com.mage.park.home.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mage.park.common.bo.PageInfo;
import com.mage.park.common.consts.ParkConsts;
import com.mage.park.common.utils.ParkUtils;
import com.mage.park.home.service.HomeService;

@Controller
public class HomeController {
	
	@Resource
	private HomeService homeService;
	
	@RequestMapping(value = "/mobile/main")
	public String toMain(HttpServletRequest request, Model model){
		//获取主界面信息
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_MAIN);
		model.addAttribute("pageInfo", pageInfo);
		
		//获取主页功能
		List<PageInfo> list = homeService.getPageFunc(ParkConsts.PAGE_MAIN, pageInfo.getPage_id());
		model.addAttribute("main", list);
		return "park/mobile/home/main";
	}
}
