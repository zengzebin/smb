package com.ssxt.dao;

import java.util.List;

import com.ssxt.model.StStbprpB;
import com.ssxt.vo.rain.MonthCumulation;

public interface StstbprpbDao {
	
	public List<StStbprpB> findgetStstbprp(String[] code);

	public List<StStbprpB> findgetStstbprp(String[] code, String type);

	public List<StStbprpB> findgetStstbprp3(String[] bounds, String type);

	public List<String> getAllLiuyuNames(String type);

	public List<StStbprpB> getSitesInLiuyu(String liuyuName, String type);

//	SELECT ADDVCD as province,STMN as sitename,YEAR(TM) AS year,MONTH(TM) as month,SUM(DYP) as dypcummulation 
//	FROM rainwater.ST_PPTN_R_NEW
//	WHERE STMN='胡家滩'
//	GROUP BY YEAR(TM),MONTH(TM)	
	public List<MonthCumulation> sitestatistics(String sitename,String startTime,String endTime) throws Exception ;
	
}
