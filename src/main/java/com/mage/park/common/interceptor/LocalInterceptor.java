package com.mage.park.common.interceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mage.park.common.consts.ParkConsts;
import com.mage.platform.framework.web.interceptor.ILocalInterceptor;

/**
 * 停车位应用 拦截器
 * 
 * @author pzh
 * @date 2017年1月18日 上午8:59:55
 */
public class LocalInterceptor implements ILocalInterceptor {

	@Override
	public void preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 设置访问设备类型
		String path = request.getPathInfo();
		if ("/".equals(path)) // 首页确定好设备类型
			this.getDeviceType(request);
	}

	/**
	 * 获取设别类型
	 * @author pzh
	 * @date 2017年1月18日 上午9:13:38
	 * @param request
	 * @return
	 */
	private String getDeviceType(HttpServletRequest request) {
		boolean isFromMobile = false;
		HttpSession session = request.getSession();
		// 检查是否已经记录访问方式（移动端或pc端）
		try {
			// 获取ua，用来判断是否为移动端访问
			String userAgent = request.getHeader("USER-AGENT").toLowerCase();
			if (null == userAgent) {
				userAgent = "";
			}
			isFromMobile = check(userAgent);
			// 判断是否为移动端访问
			if (isFromMobile) {
				session.setAttribute("ua", ParkConsts.CLIENT_DEVICE_MOBILE);
			} else {
				session.setAttribute("ua", ParkConsts.CLIENT_DEVICE_PC);
			}
		} catch (Exception e) {
		}
		return isFromMobile ? ParkConsts.CLIENT_DEVICE_MOBILE : ParkConsts.CLIENT_DEVICE_PC;
	}

	static String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i" + "|windows (phone|ce)|blackberry"
			+ "|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp" + "|laystation portable)|nokia|fennec|htc[-_]"
			+ "|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";

	static String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser" + "|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";

	// 移动设备正则匹配：手机端、平板
	static Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);
	static Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE);

	/**
	 * 检测是否是移动设备访问
	 * 
	 * @author pzh
	 * @date 2017年1月18日 上午9:11:09
	 * @param userAgent
	 * @return
	 */
	private boolean check(String userAgent) {
		if (null == userAgent) {
			userAgent = "";
		}
		// 匹配
		Matcher matcherPhone = phonePat.matcher(userAgent);
		Matcher matcherTable = tablePat.matcher(userAgent);
		if (matcherPhone.find() || matcherTable.find()) {
			return true;
		} else {
			return false;
		}
	}
}
