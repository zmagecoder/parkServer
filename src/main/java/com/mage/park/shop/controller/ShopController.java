package com.mage.park.shop.controller;

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
import com.mage.park.shop.service.ShopService;

@Controller
public class ShopController {
	
	@Resource
	private ShopService shopService;
	
	@Resource
	private HomeService homeService;
	
	/**
	 * 商城主界面
	 * @author pzh
	 * @date 2017年2月10日 下午3:53:10
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobile/shop/main")
	public String toMain(HttpServletRequest request, Model model){
		//获取主界面信息
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_SHOP_MAIN);
		model.addAttribute("pageInfo", pageInfo);
		
		//获取主页功能
		List<PageInfo> list = homeService.getPageFunc(ParkConsts.PAGE_SHOP_MAIN, pageInfo.getPage_id());
		model.addAttribute("main", list);
		return "park/mobile/shop/main";
	}
}
