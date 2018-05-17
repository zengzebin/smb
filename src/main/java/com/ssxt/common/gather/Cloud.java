package com.ssxt.common.gather;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.ssxt.common.tools.DataBaseTools;
import com.ssxt.common.tools.FileTools;
import com.ssxt.common.tools.StringTools;

public class Cloud {
	
	
	 public static void saveToFile(String http,String time) {  
	        //new一个URL对象  
		  int error=0;
		  String file;
		 
		//  String daypath="C:/apache-tomcat-7.0/wtpwebapps/Image/cloud/";
	      String daypath="/usr/local/zzb/apache-tomcat-7.0.65/webapps/Image/cloud/";
		  
		  file=time.substring(0,8);//年
          FileTools.mkdirFile(daypath+"/"+file);
		 
		 try{
			//System.out.println(http);
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
	        File imageFile = new File(daypath+"/"+file+"/"+time+".jpg");  
	       
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
		
				 String sql="INSERT INTO IMAGE (Type, Path, time) values (?,?,?)";
				 String []parameters={"1","cloud/"+file+"/"+time+".jpg",time};
				 DataBaseTools.executeUpdate(sql, parameters);
				 
			  }else if(error==1) {
				     String sql="INSERT INTO IMAGE (Type, Path, time,Status) values (?,?,?,?)";
					 String []parameters={"1",http,time,"0"};
					 DataBaseTools.executeUpdate(sql, parameters);
			  }
			
			
			     String beforeTime=StringTools.getBeforeDay(time.substring(0, 8),7);
			     System.out.println(daypath+beforeTime);
		        File file1=new File(daypath+beforeTime);
		         FileTools.deleteDir(file1);
		         String deleteDay="DELETE FROM IMAGE  WHERE  DATE_FORMAT(TIME, '%Y%m%d')='"+beforeTime+"'";
		         DataBaseTools.executeUpdate(deleteDay, null);
		}
		 
	    }  
	
	
	  
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
                String starttime = "20160220001500";//指定时间
 		        String addtime=starttime; //开始时间
 		        while(true){
 		         addtime= StringTools.addMin(addtime,30);//加30分钟
 		         String urltime=addtime+"000";
  		         System.out.println("after:"+urltime); 
  		         String url="http://pi.weather.com.cn/i/product/pic/m/sevp_nsmc_wxcl_asc_e99_achn_lno_py_"+urltime+".jpg";
  		         Cloud.saveToFile(url,StringTools.addHour(addtime,8));
                  try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
 	          }
 
	}
      	  
	}
 
	       
		
	 

 
