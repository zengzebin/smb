package com.ssxt.vo.rain;

import java.util.List;

public class AdminAreaInfo {
	private    int error;    //错误代码
	private    List<AreaInfo>  infoList; //测站信息列表
	
	private    List infoList2; //测站信息列表
	
//	public AdminAreaInfo(int error, List<AreaInfo> infoList) {
//		super();
//		this.error = error;
//		this.infoList = infoList;
//	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public List<AreaInfo> getInfoList() {
		return infoList;
	}
	public void setInfoList(List<AreaInfo> infoList) {
		this.infoList = infoList;
	}
	
	public List getInfoList2() {
		return infoList2;
	}
	public void setInfoList2(List infoList2) {
		this.infoList2 = infoList2;
	}

}
