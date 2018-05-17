package com.ssxt.common.gather;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import com.ssxt.common.tools.DataBaseTools;
import com.ssxt.common.tools.FileTools;
import com.ssxt.common.tools.StringTools;

public class Radar      extends Thread{
	
	private String Area; 
	private String AreaCode; 
	private int AreaId; 
	private String http; 
	private String urltime;
    private int type;
	 
 
	public Radar(String urltime,int type) {
		super();
	     this.type=type;
		this.urltime = urltime;
	}

	public void run() {
		
		
		 
		 String sql1="SELECT a.Code,b.Code,b.Parent FROM AREA a INNER JOIN AREA b ON(a.Parent=b.ParentId) AND a.ParentId>0";
		 
		 String sql="SELECT CODE,CODE,Parent FROM AREA WHERE  ParentId=1 OR  ParentId=0";

	  // String daypath="C:/apache-tomcat-7.0/wtpwebapps/Image/radar/";
	    String daypath="/usr/local/zzb/apache-tomcat-7.0.65/webapps/Image/radar/";

		 List list=null;
		  DataBaseTools tools=new DataBaseTools();
		  if(type==1) 
			  list= tools.executeQuery(sql1, null);
		  else
			  list= tools.executeQuery(sql, null);
 		 for (int i = 0; i < list.size(); i++) {
			  Object []o=(Object[]) list.get(i);
			  String  http="http://pi.weather.com.cn/i/product/pic/l/"
			 		+ "sevp_aoc_rdcp_sldas_ebref_"+o[1]+"_l88_pi_"+this.urltime+".gif";
			// System.out.println(http); //20160229020500000
			  if(type==1) 
				saveToFile(http, this.urltime,o[0].toString(),o[1].toString(),o[2].toString(),daypath);
			  else
			   saveToFile(http, this.urltime,o[0].toString(),o[1].toString(),o[2].toString(),daypath);

      }
 	     String beforeTime=StringTools.getBeforeDay(this.urltime.substring(0, 8),7);
	   //  System.out.println(daypath+beforeTime);
         File file=new File(daypath+beforeTime);
         FileTools.deleteDir(file);
         String deleteDay="DELETE FROM IMAGE  WHERE  DATE_FORMAT(TIME, '%Y%m%d')='"+beforeTime+"'";
         tools.executeUpdate(deleteDay, null);
	}
 
	 public static void saveToFile(String http,String time,String area,String areaCode,String AreaId, String  path) {  
	        //new一个URL对象  
		  int error=0;
		  String file;
		 
		  String daypath=path;
		  file=time.substring(0,8);//年
		  time=StringTools.addHour(time.substring(0,time.length()-3),8);
//		  month=time.substring(4,6);//月
//		  day=time.substring(6,8);//日
  // System.out.println(year+"-"+month+"-"+day);
		   FileTools.mkdirFile(daypath+"/"+file);
		   FileTools.mkdirFile(daypath+"/"+file+"/"+area);
		   FileTools.mkdirFile(daypath+"/"+file+"/"+area+"/"+areaCode);
		   String filePath=file+"/"+area+"/"+areaCode;
		 try{
		 System.out.println(http);
	        URL url = new URL(http);  
	        //打开链接  
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
	        //设置请求方式为"GET"  
	        conn.setRequestMethod("GET");  
	        //超时响应时间为5秒  
	        conn.setConnectTimeout(5 * 1000);  
	        //通过输入流获取图片数据  
	        InputStream inStream = conn.getInputStream(); 
         //得到图片的二进制数据，以二进制封装得到数据，具有通用性  
          byte[] data = FileTools.readInputStream(inStream);  
		      //new一个文件对象用来保存图片，默认保存当前工程根目录  
	        File imageFile = new File(daypath+"/"+filePath+"/"+time+".gif");  
	       
	        //创建输出流  
	        FileOutputStream outStream = new FileOutputStream(imageFile);  
	        //写入数据  
	        outStream.write(data);  
	        //关闭输出流  
	        outStream.close();  
	        BufferedImage   Image=ImageIO.read(imageFile);  
	        if (Image==null) {  
	        	System.out.println("此文件不是图片文件");
	        	FileTools.deleteFile(imageFile.getPath());
	        	error=2;
	        	return;
	        	} 
	        
		 }catch(IOException e){
			 error=1;
			 e.printStackTrace();
		 } catch (Exception e) {
			 error=1;
			e.printStackTrace();
		}finally{
			 //失败 保存url
			if(error==0){
			     String sql="INSERT INTO IMAGE (Type, Path, time,Parent) values (?,?,?,?)";
					 String []parameters={"2","radar/"+filePath+"/"+time+".gif",time,AreaId};
					  DataBaseTools.executeUpdate(sql, parameters);
				 
			  }else if(error==1) {
				   String sql="INSERT INTO IMAGE (Type, Path, time,Status,Parent) values (?,?,?,?,?)";
				  String []parameters={"2",http,time,"0",AreaId};
				 DataBaseTools.executeUpdate(sql, parameters);
			  }
		}
		 
	    }  
	 
	 public static void main(String [] sa){
			try{
					
			       long period = 8*60*1000;   
			                            
				   String starttime = "20160301092000";//指定时间
			        String addtime=starttime; //开始时间
			         while(true){
			            addtime= StringTools.addMin(addtime,5);//加30分钟
			            String urltime=addtime+"000";
				         System.out.println("after:"+urltime); 
				          Radar  ra = new Radar(urltime,1); 
				         ra.start();
		  			    Thread.sleep(period);
			          }
			 
				} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
}}
