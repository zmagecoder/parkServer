package com.mage.park.home.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class HomeService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	public void test(){
		logger.info("===============>>>");
	}
	
}
