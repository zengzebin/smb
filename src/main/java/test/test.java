package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssxt.common.tools.StringTools;
import com.ssxt.model.City;
import com.ssxt.vo.Boundary.ProvinceBoundary;
import com.ssxt.vo.rain.AccDailyDayRainFall;

import net.sf.json.JSONObject;

public class test {
	
	public static void findStpptnrDayRainFall(String[] StationNo, String DateTime, int DayNumber) {
		StringBuffer hql=new StringBuffer("select  stcd,tm,");
		StringBuffer whereday=new StringBuffer();
		String  STCD=new String();
		for (int i = 0; i < StationNo.length; i++) {
			STCD+="'"+StationNo[i]+"',";
		}
		if(StationNo.length>0)
			STCD=STCD.substring(0, STCD.length()-1);
		
 		for(int i=1;i<=DayNumber;i++){
 			String time=StringTools.getSpecifiedDayAfter(DateTime, "yyy-mm-dd", i);
 			if(i!=DayNumber){
 				hql.append("(CASE  WHEN  tm='"+time+" 08:00:00' THEN dyp ELSE 0.0 END) d"+i+",");
 	            whereday.append(" tm='"+time+" 08:00:00' OR");
 			}else{
 				hql.append("(CASE  WHEN  tm='"+time+" 08:00:00' THEN dyp ELSE 0.0 END) d"+i+" ");
 	            whereday.append(" tm='"+time+" 08:00:00'");
 			}
           
          }    
 		hql.append(" from StPptnR where stcd in(").append(STCD).append(") and ").append("(").append(whereday).append(") ");
 		System.out.println(hql.toString());
	}
	
	public static void get(){
		System.out.println("========");
	}
	
	 public static ProvinceBoundary getProvinceBoundary(String path){
		 ProvinceBoundary p=new ProvinceBoundary();
      	 String ProvinceName = path.substring(path.lastIndexOf(System.getProperty("file.separator")) + 1,path.lastIndexOf('.') ); 

		  File file=new File(path);
		  String encoding="GBK";
		  String result = new String();
		  try {
          if(file.isFile() && file.exists()){ //判断文件是否存在
              InputStreamReader read = new InputStreamReader(
              new FileInputStream(file),encoding);//考虑到编码格式
              BufferedReader bufferedReader = new BufferedReader(read);
              String s = new String();
              while((s = bufferedReader.readLine()) != null){
           	   result+=s;  
              }
             read.close();
	          } 
      } catch (Exception e) {
	       System.out.println("读取文件内容出错");
	       e.printStackTrace();
	   }
		  p.setPoints(result);
		  p.setProvinceName(ProvinceName);
		  return p;
	 }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//  String path=request.getSession().getServletContext().getRealPath("/WEB-INF/sunkingWater/cityBoundyDatas/provinceBoundyDatas");
    	try{
    		
    	List <ProvinceBoundary>listinfo=new ArrayList<ProvinceBoundary>();
		File file=new File("D:"+System.getProperty("file.separator") +"sunkingWater"+
				System.getProperty("file.separator") +"provinceBoundyDatas");
    	  File[] tempList = file.listFiles();
     	  System.out.println("该目录下对象个数："+tempList.length);
    	  for (int i = 0; i <tempList.length; i++) {
    	   if (tempList[i].isFile()) {
    	//   System.out.println("文     件："+tempList[i]);
    	   String path=tempList[i].toString();
            ProvinceBoundary p= getProvinceBoundary(path);
           listinfo.add(p);
            }
    	  }
    	  Map<String,Object> map =new HashMap<String,Object>();
          map.put("error", "0");
          map.put("infoList", listinfo);
          JSONObject jsonObject = JSONObject.fromObject(map);
        //  System.out.println(jsonObject.toString());
    	  }catch (Exception e) {
			// TODO: handle exception
    		  e.printStackTrace();
		}
	}
    	 
	

}
