package com.jet.dsm.redis;


import com.jet.dsm.mysql.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * 
 * @Description: 日冻结数据上传
 * @author zhang kaifeng
 * @date 2017年3月8日
 */
@Component
public class Job {

	private static final Logger logger = LoggerFactory.getLogger(Job.class);

	@Resource(name = "RedisCacheStorageImpl")
	private RedisCacheStorage redisCacheStorage;

	@Scheduled(cron = " 0 39 * * * *")
	public void sendPointCurve() {
		System.out.println("yunxing");
		SysUser sysUser = new SysUser();
		sysUser.setUserName("zhangkaifeng");
		sysUser.setUserId("001");
		try {
			redisCacheStorage.hset("user","002",sysUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
