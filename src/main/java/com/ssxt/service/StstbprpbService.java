package com.ssxt.service;

import java.util.List;

import com.ssxt.model.StStbprpB;
import com.ssxt.vo.rain.MonthCumulation;

public interface StstbprpbService {

	public List<StStbprpB> findgetStstbprp(String[] code);

	public List<StStbprpB> findgetStstbprp(String[] areaNo, String type);

	public List<StStbprpB> findgetStstbprp2(String[] code);

	public List<StStbprpB> findgetStstbprp3(String[] bounds, String type);

	public List<String> getAllLiuyuNames(String type);

	public List<StStbprpB> getSitesInLiuyu(String liuyuName, String type);
	
	public List<MonthCumulation> sitestatistics(String sitename,String startTime,String endTime) throws Exception;

}
