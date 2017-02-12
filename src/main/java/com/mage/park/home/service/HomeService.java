package com.mage.park.home.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mage.park.common.bo.PageInfo;
import com.mage.park.common.consts.ParkConsts;
import com.mage.park.common.utils.ParkCache;
import com.mage.platform.utils.SqlUtils;

@Service
public class HomeService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 获取主功能列表
	 * @author pzh
	 * @date 2017年2月9日 下午2:52:56
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PageInfo> getPageFunc(String pageCode, String pageId){
		try{
			List<PageInfo> list = (List<PageInfo>)ParkCache.getInstance().get(pageCode + "_sub_list");
			if(null == list || list.isEmpty()){
				String sql = "SELECT * FROM p_page_info a WHERE a.PARENT_ID = ? and state = " + ParkConsts.VALIDE_STATE + "";
				list = SqlUtils.getExecutor().queryForList(sql, PageInfo.class, pageId);
				ParkCache.getInstance().set(pageCode + "_sub_list", list);
			}
			return list;
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return null;
	}
	
}
