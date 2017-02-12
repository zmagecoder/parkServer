package com.mage.park.transaction.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mage.park.common.bo.PageInfo;
import com.mage.park.common.consts.ParkConsts;
import com.mage.park.common.utils.ParkUtils;
import com.mage.park.home.service.HomeService;
import com.mage.park.transaction.service.TransactionService;
import com.mage.platform.model.AssembleJSON;

@Controller
public class TransactionController {
	
	@Resource
	private TransactionService transactionService;
	
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
	@RequestMapping(value = "/mobile/transaction/main")
	public String toMain(HttpServletRequest request, Model model){
		//获取主界面信息
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_TRANSACTION_HALL);
		model.addAttribute("pageInfo", pageInfo);
		
		//获取主页功能
		List<PageInfo> list = homeService.getPageFunc(ParkConsts.PAGE_TRANSACTION_HALL, pageInfo.getPage_id());
		model.addAttribute("main", list);
		return "park/mobile/transaction/main";
	}
	
	/**
	 * 售卖界面
	 * @author pzh
	 * @date 2017年2月9日 下午6:47:50
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobile/transaction/sell")
	public String sell(HttpServletRequest request, Model model){
		//获取主界面信息
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_SELL_COIN);
		model.addAttribute("pageInfo", pageInfo);
		
		return "park/mobile/transaction/sell";
	}
	
	/**
	 * 添加订单
	 * @author pzh
	 * @date 2017年2月9日 下午6:10:53
	 * @param sellInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mobile/transaction/addOrder")
	public AssembleJSON addOrder(Map<String, Object> orderInfo){
		String errorMsg = transactionService.addOrder(orderInfo);
		if(!StringUtils.isEmpty(errorMsg))
			return AssembleJSON.FAILURE(errorMsg);
		return AssembleJSON.SUCCESS;
	}
	
	/**
	 * 定向售卖记录
	 * @author pzh
	 * @date 2017年2月9日 下午6:50:52
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobile/transaction/sellRecord")
	public String sellRecord(HttpServletRequest request, Model model){
		//获取主界面信息
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_SELL_RECORD);
		model.addAttribute("pageInfo", pageInfo);
		
		List<Map<String, Object>> list = transactionService.getSellRecord(ParkConsts.ORDER_TYPE_1);		//定向购买
		model.addAttribute("sellRecord", list);
		return "park/mobile/transaction/sellRecord";
	}
	
	/**
	 * 委托售卖记录
	 * @author pzh
	 * @date 2017年2月9日 下午6:52:00
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobile/transaction/authorizeRecord")
	public String authorizeRecord(HttpServletRequest request, Model model){
		//获取主界面信息
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_AUTHORIZE_RECORD);
		model.addAttribute("pageInfo", pageInfo);
		
		List<Map<String, Object>> list = transactionService.getSellRecord(ParkConsts.ORDER_TYPE_2);		//定向购买
		model.addAttribute("sellRecord", list);
		return "park/mobile/transaction/authorizeRecord";
	}
	
	/**
	 * 购买记录
	 * @author pzh
	 * @date 2017年2月9日 下午6:57:57
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobile/transaction/buyCoinRecord")
	public String buyCoinRecord(HttpServletRequest request, Model model){
		//获取主界面信息
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_BUY_COIN_RECORD);
		model.addAttribute("pageInfo", pageInfo);
		
		List<Map<String, Object>> list = transactionService.getBuyRecord();		//定向购买
		model.addAttribute("sellRecord", list);
		return "park/mobile/transaction/buyCoinRecord";
	}
	
}
