package com.mage.park.lot.controller;

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
import com.mage.park.lot.bo.Notice;
import com.mage.park.lot.service.LotService;
import com.mage.park.user.bo.User;
import com.mage.platform.model.AssembleJSON;
import com.mage.platform.utils.EncryptUtils;
import com.mage.platform.utils.SqlUtils;

@Controller
public class LotController {
	
	@Resource
	private LotService lotService;
	
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
	@RequestMapping(value = "/mobile/lot/main")
	public String toMain(HttpServletRequest request, Model model){
		//获取主界面信息
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_LOT);
		model.addAttribute("pageInfo", pageInfo);
		
		//获取主页功能
		List<PageInfo> list = homeService.getPageFunc(ParkConsts.PAGE_LOT, pageInfo.getPage_id());
		model.addAttribute("main", list);
		return "park/mobile/lot/main";
	}
	
	/**
	 * 公告里列表界面
	 * @author pzh
	 * @date 2017年2月9日 下午3:18:50
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobile/notices")
	public String getNotices(HttpServletRequest request, Model model){
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_NOTICE);
		model.addAttribute("pageInfo", pageInfo);
		//公告列表信息
		List<Notice> list = lotService.getNotices();
		model.addAttribute("notices", list);
		//获取主界面信息
		return "park/mobile/lot/notices";
	}
	
	/**
	 * 公告里详情界面
	 * @author pzh
	 * @date 2017年2月9日 下午3:18:50
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobile/notices/detail")
	public String getNoticeDetail(HttpServletRequest request, Model model, String noticeId){
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_NOTICE_DETAIL);
		model.addAttribute("pageInfo", pageInfo);
		//公告详情信息
		Map<String, Object> notice = lotService.getNoticesDetail(noticeId);
		model.addAttribute("notice", notice);
		return "park/mobile/lot/noticesDetail";
	}
	
	/**
	 * 收益走势
	 * @author pzh
	 * @date 2017年2月9日 下午4:04:29
	 * @param request
	 * @param model
	 * @param noticeId
	 * @return
	 */
	@RequestMapping(value = "/mobile/lot/growth")
	public String getGrowth(HttpServletRequest request, Model model, String noticeId){
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_GROWTH_TREND);
		model.addAttribute("pageInfo", pageInfo);
		//TODO 收益趋势数据
		return "park/mobile/lot/growth";
	}
	
	/**
	 * 车位信息
	 * @author pzh
	 * @date 2017年2月9日 下午4:21:51
	 * @param request
	 * @param model
	 * @param noticeId
	 * @return
	 */
	@RequestMapping(value = "/mobile/lot/space")
	public String getLotSpace(HttpServletRequest request, Model model, String noticeId){
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_MY_LOT_SPACE);
		model.addAttribute("pageInfo", pageInfo);
		//车位具体信息
		Map<String, Object> lotSpace = lotService.getMyLotSpace();
		model.addAttribute("lotSpace", lotSpace);
		return "park/mobile/lot/space";
	}
	
	/**
	 * 好友列表
	 * @author pzh
	 * @date 2017年2月9日 下午4:37:46
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mobile/lot/freinds")
	public String getFriends(HttpServletRequest request, Model model){
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_FRIEND_LIST);
		model.addAttribute("pageInfo", pageInfo);
		//获取好友列表
		List<Map<String, Object>> friends = lotService.getFriends();
		model.addAttribute("friends", friends);
		return "park/mobile/lot/freinds";
	}
	
	/**
	 * 好友车位
	 * @author pzh
	 * @date 2017年2月9日 下午4:38:05
	 * @param request
	 * @param model
	 * @param friendId
	 * @return
	 */
	@RequestMapping(value = "/mobile/lot/friendSapce")
	public String getFriendSpace(HttpServletRequest request, Model model, Integer friendId){
		//TODO 判断friendId 是否为当前登录用户的朋友
		
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_FRIEND_LOT_SPACE);
		model.addAttribute("pageInfo", pageInfo);
		
		//车位具体信息
		Map<String, Object> lotSpace = lotService.getFriendSpace(friendId);
		model.addAttribute("lotSpace", lotSpace);
		return "park/mobile/lot/fSpace";
	}
	
	/**
	 * 开发新车位
	 * @author pzh
	 * @date 2017年2月9日 下午4:43:56
	 * @param request
	 * @param model
	 * @param friendId
	 * @return
	 */
	@RequestMapping(value = "/mobile/lot/newSpace")
	public String newSpace(HttpServletRequest request, Model model, Integer friendId){
		PageInfo pageInfo = ParkUtils.getPageInfo(ParkConsts.PAGE_NEW_SPACE);
		model.addAttribute("pageInfo", pageInfo);
		
		return "park/mobile/lot/newSpace";
	}
	
	/**
	 * 保存新用户
	 * @author pzh
	 * @date 2017年2月9日 下午4:51:13
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/mobile/lot/saveUser")
	public AssembleJSON saveUser(User user){
		try{
			String userPass = user.getUser_pass();
			if(StringUtils.isEmpty(userPass) || StringUtils.isEmpty(user.getUser_code())
					|| StringUtils.isEmpty(user.getUser_name()))
				return AssembleJSON.FAILURE("参数不正确");
			user.setUser_id(SqlUtils.getExecutor().getSequences("sys_user", "", 20));
			user.setUser_pass(EncryptUtils.encrypt(userPass, EncryptUtils.MD5));
			SqlUtils.getExecutor().insert("sys_user", user); //插入数据
		}catch(Exception e){
			return AssembleJSON.FAILURE("推荐好友失败");
		}
		return AssembleJSON.SUCCESS;
	}
	
}
