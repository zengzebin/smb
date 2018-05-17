package com.ssxt.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssxt.dao.StstbprpbDao;
import com.ssxt.model.StStbprpB;
import com.ssxt.service.StstbprpbService;
import com.ssxt.vo.rain.MonthCumulation;

@Service("ststbprpbService")
public class StstbprpbImpl implements StstbprpbService {

	@Autowired
	StstbprpbDao ststbprpbDao;
	 
	public List<StStbprpB> findgetStstbprp(String [] code) {
		// TODO Auto-generated method stub
		return 	ststbprpbDao.findgetStstbprp(code);
	}
	
	public List<StStbprpB> findgetStstbprp(String[] areaNo, String type) {
		// TODO Auto-generated method stub
		return 	ststbprpbDao.findgetStstbprp(areaNo, type);
	}
	
	public List<StStbprpB> findgetStstbprp2(String [] code) {
		// TODO Auto-generated method stub
	
		return 	ststbprpbDao.findgetStstbprp(code);
	}
	
	public List<StStbprpB> findgetStstbprp3(String[] bounds, String type) {
		// TODO Auto-generated method stub
		return ststbprpbDao.findgetStstbprp3(bounds, type);
	}

	public List<String> getAllLiuyuNames(String type) {
		// TODO Auto-generated method stub
		return ststbprpbDao.getAllLiuyuNames(type);
	}

	public List<StStbprpB> getSitesInLiuyu(String liuyuName, String type) {
		// TODO Auto-generated method stub
		return ststbprpbDao.getSitesInLiuyu(liuyuName, type);
	}

	public List<MonthCumulation> sitestatistics(String sitename,String startTime,String endTime) throws Exception {
		// TODO Auto-generated method stub
		return ststbprpbDao.sitestatistics(sitename,startTime,endTime); 
	}

	 




}
