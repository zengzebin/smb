package com.ssxt.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ssxt.common.gather.Rain;
import com.ssxt.common.gather.River;
import com.ssxt.common.gather.Rsvr;

import java.util.Date;

@Component
public class MyTaskAnnotation {
 
	/**
	 *  启动时执行一次，之后每隔4小时执行一次
	 */
	@Scheduled(fixedRate = 60000*60*4)
	public void print() {
		 System.out.println("雨水情采集开始=" + new Date());
		 new Rain().run(); //水情
		 new River().run();//河流
		 new Rsvr().run(); //水库
	} 
}
