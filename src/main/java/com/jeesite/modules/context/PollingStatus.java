package com.jeesite.modules.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class PollingStatus {
	public static Map<String, Long> infoMap = new ConcurrentHashMap<String, Long>();
	static {
		System.out.println("开始加载PollingStatus类");
		new Thread(new Task(infoMap)).start();
	}
}