package com.mage.park.growth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mage.park.common.utils.ParkUtils;
import com.mage.park.user.bo.User;
import com.mage.platform.utils.SqlUtils;

@Service
public class GrowthService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 获取车模奖励记录
	 * @author pzh
	 * @date 2017年2月9日 下午5:22:16
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getModels(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try{
			User user = ParkUtils.getCurrUser();
			String sql = "select * from p_car_model where a.user_id = ?";
			list = SqlUtils.getExecutor().queryForList(sql, user.getUser_id());
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return list;
	}
	
	/**
	 * 车辆保养记录
	 * @author pzh
	 * @date 2017年2月9日 下午5:35:16
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMaintainList(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try{
			User user = ParkUtils.getCurrUser();
			String sql = "select * from p_maintain where a.user_id = ?";
			list = SqlUtils.getExecutor().queryForList(sql, user.getUser_id());
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return list;
	}
	
	/**
	 * 洗车记录
	 * @author pzh
	 * @date 2017年2月9日 下午5:40:50
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getWashList(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try{
			User user = ParkUtils.getCurrUser();
			String sql = "select * from p_wash_car where a.user_id = ?";
			list = SqlUtils.getExecutor().queryForList(sql, user.getUser_id());
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return list;
	}
	
	/**
	 * 购车记录
	 * @author pzh
	 * @date 2017年2月9日 下午5:43:32
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> buyCarList(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try{
			User user = ParkUtils.getCurrUser();
			String sql = "select * from p_buy_car where a.user_id = ?";
			list = SqlUtils.getExecutor().queryForList(sql, user.getUser_id());
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return list;
	}
}
