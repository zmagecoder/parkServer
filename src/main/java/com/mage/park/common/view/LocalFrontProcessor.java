package com.mage.park.common.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mage.park.common.consts.ParkConsts;
import com.mage.park.common.utils.ParkUtils;
import com.mage.platform.framework.web.processor.ILocalFrontProcessor;

/**
 * 设置系统语言
 * @author pzh
 * @date 2017年2月9日 上午11:12:02
 */
public class LocalFrontProcessor implements ILocalFrontProcessor {

	@Override
	public void process(Map<String, Object> model, HttpServletRequest request) {
		String language = ParkUtils.getLanguage();			//获取当前源
		model.put(ParkConsts.LANGUAGE_PREX, language);
	}
}
