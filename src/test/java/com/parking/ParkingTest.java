package com.parking;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.mage.platform.framework.database.IDaoSupport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
									"classpath:spring-mybatis.xml",
									"classpath:spring/common-spring.xml",
									"classpath:spring/jdbc-spring.xml"
								  })

public class ParkingTest{
	
	private static Logger logger = Logger.getLogger(ParkingTest.class);
	
	@Test
	public void jsonToMap(){
		String json = "{\"loginBtn\": {\"ename\": \"login\",\"cname\": \"登录\"},\"userName\": {\"ename\": \"user name\",\"cname\": \"用户名\"},\"password\":{\"ename\": \"password\",\"cname\": \"密码\"},\"checkCode\":{\"ename\": \"yanzhengma\",\"cname\":\"验证码\"}}";
		Map testMap = JSON.parseObject(json, Map.class);
		logger.info("================>>>");
	}
	
	public void getRootPath(){
		logger.info(System.getProperty("CONFIG"));
		logger.info(this.getClass().getResource("/").getPath());
		logger.info(System.getProperty("user.dir"));
	}
}