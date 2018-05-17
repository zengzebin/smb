package com.ssxt.common.tools;

import java.io.FileNotFoundException;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Value;

public class InitConfig {
	private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(InitConfig.class);
	
	@Value("${cs.consumerThreadCount}")	
	public int consumerThreadCount;
	
	@Value("${cs.consumerSleepTime}")
	public int consumerSleepTime;
	
	

	@Value("${init_service}")
	public String init_service;
	
	@Value("${init_client}")
	public String init_client;
	
	@Value("${ug.filePath}")
	public String ug_filePath;
	
	public String basePath=null;
	public void init() throws FileNotFoundException, DocumentException{
		basePath=this.getClass().getResource("/").getPath();	}
}
