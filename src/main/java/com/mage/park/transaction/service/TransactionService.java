package com.mage.park.transaction.service;

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
public class TransactionService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
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
	 * 添加订单
	 * @author pzh
	 * @date 2017年2月9日 下午6:09:05
	 * @param sellInfo
	 * @return
	 */
	public String addOrder(Map<String, Object> sellInfo){
		String errorMsg = "";
		try{
			SqlUtils.getExecutor().insert("p_order", sellInfo);
		}catch(Exception e){
			errorMsg = "系统异常";
		}
		return errorMsg;
	}
	
	/**
	 * 售卖记录
	 * @author pzh
	 * @date 2017年2月9日 下午6:43:55
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSellRecord(Integer orderType){
		try{
			User user = (User)ThreadContextHolder.getHttpRequest().getSession().getAttribute(ParkConsts.USER_SESSION);
			String userId = user.getUser_id();
			String sql = "SELECT a.*  FROM p_order a WHERE a.SELLER = ? and a.ORDER_TYPE = ?";
			List<Map<String, Object>> list = SqlUtils.getExecutor().queryForList(sql, userId, orderType);
			return list;
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 购买记录
	 * @author pzh
	 * @date 2017年2月9日 下午6:57:17
	 * @param orderType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getBuyRecord(){
		try{
			User user = (User)ThreadContextHolder.getHttpRequest().getSession().getAttribute(ParkConsts.USER_SESSION);
			String userId = user.getUser_id();
			String sql = "SELECT a.*  FROM p_order a WHERE a.BUYER = ?";
			List<Map<String, Object>> list = SqlUtils.getExecutor().queryForList(sql, userId);
			return list;
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return null;
	}
}
