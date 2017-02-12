package com.mage.park.common.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 简易缓存类
 * @author pzh
 * @date 2017年2月9日 上午10:22:34
 */
public class ParkCache {
	
	private ParkCache(){
		data = new ConcurrentHashMap<String, Object>();
	}
	
	private static ParkCache inst = null;
	
	public static synchronized ParkCache getInstance(){
		if(null == inst)
			inst = new ParkCache();
		return inst;
	}
	
	private  Map<String, Object> data = null;
	
	public void set(String key, Object value){
		data.put(key, value);
	}
	
	public Object get(String key){
		return data.get(key);
	}
	
	public void clear(){
		data.clear();
	}
}
