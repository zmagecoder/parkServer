package com.mage.park.message.controller;

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
import com.mage.park.message.service.MessageService;

@Controller
public class MessageController {
	
	@Resource
	private MessageService messageService;
	
	@Resource
	private HomeService homeService;
	
	/**
	 * 消息主界面
	 * @author pzh
	 * @date 2017年2月10日 下午3:52:50
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobile/msg/main")
	public String toMain(HttpServletRequest request, Model model){
		//获取主界面信息
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_MESSASGE_MAIN);
		model.addAttribute("pageInfo", pageInfo);
		
		//获取主页功能
		List<PageInfo> list = homeService.getPageFunc(ParkConsts.PAGE_MESSASGE_MAIN, pageInfo.getPage_id());
		model.addAttribute("main", list);
		return "park/mobile/msg/main";
	}
	
}
