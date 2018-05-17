package test;

 

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;  
 import java.io.FileOutputStream;  
 import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import com.ssxt.common.gather.Rain;  
  
 public class DowImage    extends TimerTask{  
  
	 
	 public void run(){
		 Date now = new Date(); 
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");//可以方便地修改日期格式
		 String  time= dateFormat.format( now ); 
		 System.out.println(time);
		 String fileName="c:\\"+time+".gif";
		 String url="http://pi.weather.com.cn/i/product/pic/l/sevp_nsmc_wxcl_asc_e99_achn_lno_py_20160223061500000.jpg";
		 System.out.println(url);
		 saveToFile(url,fileName);
	 }
 
	 
	 /**
	  * 下载图图片
	  * @param destUrl 地址
	  * @param time 时间
	  */
	 public  static void saveToFile(String destUrl,String fileName) {  
  				 HttpURLConnection  httpUrl = null;  
				 URL url = null;  
 			     try {
				 url = new URL(destUrl);
				
				 httpUrl = (HttpURLConnection) url.openConnection(); 
				 
				// httpUrl.connect();  
				  //设置请求方式为"GET"  
				 httpUrl.setRequestMethod("GET");  
			        //超时响应时间为5秒  
				 httpUrl.setConnectTimeout(5 * 1000);  
 				// bis = new BufferedInputStream(httpUrl.getInputStream()); 
				
			     Slicing c=new Slicing();
				 c.cut(httpUrl.getInputStream(),fileName);
				 //resizeImage(httpUrl.getInputStream(),out,500,"gif");
				  } catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  finally{
					 httpUrl.disconnect(); 
				}
 }
				 
  
 public static void main(String[] args) {  
 // TODO Auto-generated method stub  
	 DowImage d=new DowImage();
	 d.run();
	   Calendar calendar = Calendar.getInstance();  
	   calendar.set(Calendar.HOUR_OF_DAY, 9); // 控制时  
	    calendar.set(Calendar.MINUTE, 00); // 控制分  
	    calendar.set(Calendar.SECOND, 00); // 控制秒  
	    Timer timer = new Timer();  
	    Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00  
	    //间隔：1小时
       //long period = 1000 * 60 * 60;    
       //测试时间每分钟一次
	    long period =1000 * 60 * 3;      
	    System.out.println("=========================调用=========================");
	    DowImage  dw=new DowImage();  
	    timer.schedule(dw, time,150000);
   
  }  
 }  