package com.mage.park.common.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.mage.park.common.bo.PageInfo;
import com.mage.platform.utils.SqlUtils;

/**
 * 通用服务处理类
 * @author pzh
 * @date 2017年2月9日 上午9:47:20
 */
@Service
public class CommonService {
	
	/**
	 * 根据界面编码获取界面信息
	 * @author pzh
	 * @date 2017年2月9日 上午10:20:44
	 * @param pageCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageInfo getPageInfo(String pageCode){
		String sql = "SELECT * FROM p_page_info a WHERE a.PAGE_CODE = ?";
		PageInfo pageInfo =	(PageInfo) SqlUtils.getExecutor().queryForObject(sql, PageInfo.class, pageCode);
		if(!StringUtils.isEmpty(pageInfo.getPage_element())){
			pageInfo.setPageEle(JSON.parseObject(pageInfo.getPage_element(), Map.class));
		}
		return pageInfo;
	}
}
