package com.mage.park.lot.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mage.park.common.consts.ParkConsts;
import com.mage.park.common.utils.ParkUtils;
import com.mage.park.lot.bo.Notice;
import com.mage.park.user.bo.User;
import com.mage.platform.framework.context.ThreadContextHolder;
import com.mage.platform.utils.SqlUtils;

@Service
public class LotService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 获取公告列表
	 * @author pzh
	 * @date 2017年2月9日 下午2:52:56
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Notice> getNotices(){
		try{
			String sql = "SELECT * FROM p_notice a WHERE a.NOTICE_TYPE = ?";
			String language = ParkUtils.getLanguage();
			List<Notice> list = SqlUtils.getExecutor().queryForList(sql, Notice.class, language);
			return list;
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 获取公告详情
	 * @author pzh
	 * @date 2017年2月9日 下午4:09:55
	 * @param noticeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getNoticesDetail(String noticeId){
		try{
			String sql = "SELECT a.NOTICE_CONTENT, a.NOTICE_TIME, a.NOTICE_TITLE FROM p_notice a WHERE a.NOTICE_ID = ?";
			Map<String, Object> notice = SqlUtils.getExecutor().queryForMap(sql, noticeId);
			return notice;
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 获取我的车位
	 * @author pzh
	 * @date 2017年2月9日 下午4:10:06
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getMyLotSpace(){
		try{
			User user = (User)ThreadContextHolder.getHttpRequest().getSession().getAttribute(ParkConsts.USER_SESSION);
			String userId = user.getUser_id();
			String sql = "SELECT b.ACCOUNT, a.CM_NO, a.BY_NO, a.XC_NO FROM p_park_lot a, p_user_account b "
					+ "WHERE a.USER_ID = b.USER_ID AND b.USER_ID = ?";
			Map<String, Object> lotSpace = SqlUtils.getExecutor().queryForMap(sql, userId);
			return lotSpace;
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 获取朋友车位
	 * @author pzh
	 * @date 2017年2月9日 下午4:36:22
	 * @param friendId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getFriendSpace(Integer friendId){
		try{
			String sql = "SELECT b.ACCOUNT, a.CM_NO, a.BY_NO, a.XC_NO FROM p_park_lot a, p_user_account b "
					+ "WHERE a.USER_ID = b.USER_ID AND b.USER_ID = ?";
			Map<String, Object> lotSpace = SqlUtils.getExecutor().queryForMap(sql, friendId);
			return lotSpace;
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 获取好友列表
	 * @author pzh
	 * @date 2017年2月9日 下午4:31:40
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getFriends(){
		try{
			User user = (User)ThreadContextHolder.getHttpRequest().getSession().getAttribute(ParkConsts.USER_SESSION);
			String userId = user.getUser_id();
			String sql = "SELECT b.USER_CODE, b.USER_NAME  FROM p_friends a , sys_user b WHERE a.FRIEND_ID = b.USER_ID AND a.USER_ID = ?";
			List<Map<String, Object>> list = SqlUtils.getExecutor().queryForList(sql, userId);
			return list;
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return null;
	}
	
}
