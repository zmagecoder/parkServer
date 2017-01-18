package com.mage.park.home.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mage.park.common.consts.ParkConsts;
import com.mage.park.home.service.HomeService;

@Controller
public class HomeController {
	
	@Resource
	private HomeService homeService;
	
	@RequestMapping(value = "/")
	public String toIndex(HttpServletRequest request, Model model){
		String deviceType = (String)request.getSession().getAttribute("ua");
		if(ParkConsts.CLIENT_DEVICE_MOBILE.equals(deviceType)){
			return "park/mobile/index";
		}else {
			return "park/pc/index";
		}
	}
}
