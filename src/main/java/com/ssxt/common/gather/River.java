package com.ssxt.common.gather;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

 import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.ssxt.common.tools.ConstParam;
import com.ssxt.common.tools.DataBaseTools;
import com.ssxt.common.tools.FileTools;
import com.ssxt.common.tools.StringTools;

public class River {
	private static Logger log = Logger.getLogger(River.class);

	  public void run(){
		  try {
	 
				Date now = new Date(); 
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");//可以方便地修改日期格式
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd-HH");//记录信息
				SimpleDateFormat filetime = new SimpleDateFormat("yyyyMMdd");//文件夹名称
		        String  time= dateFormat.format( now ); 
		    	System.out.println("河道采集开始时间为"+dateFormat1.format(now));
		        // String daypath="D:/home/zengzb/river/";//保存路径与读取路径
		        String daypath=ConstParam.saveRiverFile;
		 		WebClient webClient = new WebClient(BrowserVersion.CHROME); 
				//设置webClient的相关参数 
				webClient.getOptions().setJavaScriptEnabled(true); 
				webClient.getOptions().setCssEnabled(false); 
		 		webClient.setAjaxController(new NicelyResynchronizingAjaxController()); 
		 		webClient.getOptions().setThrowExceptionOnScriptError(false); 
				//模拟浏览器打开一个目标网址 
		 		    HtmlPage rootPage= webClient.getPage("http://xxfb.hydroinfo.gov.cn/ssIndex.html?type=3");
					System.out.println("为了获取js执行的数据 线程开始沉睡等待"); 
					Thread.sleep(10000);//主要是这个线程的等待 因为js加载也是需要时间的
					System.out.println("线程结束沉睡"); 
					String STCD,TM,txt,WPTN=null;
		    		double Z,Q,WRZ;
		    		String shuzu[];
		    		int num=0;
		    		String content=new String();
  	     	       List <HtmlTableRow>trList=(List<HtmlTableRow>) rootPage.getByXPath("//div[@id='hdtable']//tr[contains(@class,'td-show')]");  // 获取div为hdtable所有的tr
			    		for (int i = 0; i < trList.size(); i++) {
			    	
			               List <HtmlTableCell> td=trList.get(i).getCells();//获取一个tr下面的所有td 河道有8个td
			    		  TM=time+"-"+td.get(4).asText().substring(0,5);//获取时间
			    		  //#####################
			    		   txt=td.get(5).asXml();//获取STCD
			    		   shuzu=txt.substring(txt.indexOf('(')+1, txt.indexOf(')')).replaceAll("'","").split(",");
			               STCD=shuzu[0];
			             //#####################获取水位与水势shuzu
			               shuzu=td.get(5).asText().replace(" ", ",").split(",");
			               Z=StringTools.convert_Double(shuzu[0]);
			                if(shuzu[1].trim().equals("↓"))
			                	WPTN="4";
			                if(shuzu[1].trim().equals("↑"))
			                	WPTN="5";
			                if(shuzu[1].trim().equals("—"))
			                	WPTN="6";
			             //#################流量
			                Q=StringTools.convert_Double(td.get(6).asText()) ;
			                
			                WRZ= StringTools.convert_Double(td.get(7).asText().trim());
			                System.out.println(TM+" "+" stcd:"+STCD+" 水位:"+Z+" 水势:"+WPTN+" 流量"+Q+"水位线"+StringTools.convertDouble(td.get(7).asText().trim()));
			                content+=STCD+","+TM+","+Z+","+Q+","+WPTN+","+WRZ+System.getProperty("line.separator");
			              
			    		}
					 
				     FileTools.mkdirFile(daypath+filetime.format(now));//创建文件夹
				      /** *  写入文件  */
				     String fileName=daypath+filetime.format(now)+"/"+dateFormat1.format(now)+".txt";
				     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				     new FileOutputStream(fileName), "UTF-8"));
				     writer.write(content);
				     writer.close();
				       /** * **************/
				     saveRiver(fileName,dateFormat1.format(now));
				       String beforeTime=StringTools.getBeforeDay(filetime.format(now),7);
				       System.out.println("删除文件夹"+daypath+beforeTime);
				       File deleteFile =new File(daypath+beforeTime);
				       FileTools.deleteDir(deleteFile);
		 	 	} catch (FailingHttpStatusCodeException e) {
		          // TODO Auto-generated catch block
		 			 log.error("河道采集",e); 
					e.printStackTrace();
		 		} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
		 			log.error("河道采集",e); 
					e.printStackTrace();
				 } catch (IOException e) {
					// TODO Auto-generated catch block
					 log.error("河道采集",e); 
					 e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					log.error("河道采集",e); 
					e.printStackTrace();
				} 
			 
				 
	  }
 	  public static void saveRiver(String fileName,String time){
		    String  txt;//一行数据
		    int num=0;
		    String rain;//天气
		    String shuzu[]=new String[4];
		    String sql="INSERT INTO ST_RIVER_R (STCD, TM, Z,Q,WPTN,addTime) values (?,?,?,?,?,?)";
		    String sql1="INSERT INTO ST_RVFCCH_B (STCD, WRZ,MODITIME) values (?,?,?)";
		    String count;
		    PreparedStatement ps,ps2=null;//插入
		    Statement stmt;//查询
		    ResultSet rs = null;//结果
		   Connection con=null;
			try {
				File file = new File(fileName); 
				con=DataBaseTools.getCon();//获取jdbc连接
				ps = con.prepareStatement(sql);
				ps2= con.prepareStatement(sql1);
			    stmt= con.createStatement();
			    InputStreamReader str = new InputStreamReader(new FileInputStream(fileName),"UTF-8");
			    BufferedReader br = new BufferedReader(str);
		  
				while((txt = br.readLine())!=null){//使用readLine方法，一次读一行
					shuzu=txt.split(",");
					count="SELECT count(1)  FROM  ST_RIVER_R  WHERE TM='"+shuzu[1].trim()+"' AND STCD='"+shuzu[0].trim()+"'";
					rs = stmt.executeQuery(count);
 					rs.next();
 					 num = rs.getInt(1);//是否已经有记录有返回1,没有返回0
 
					 if(num==0){
				   //河道水情表
                   ps.setString(1, shuzu[0].trim());
				   ps.setString(2, shuzu[1].trim());
				   ps.setString(3, shuzu[2].trim());
				   ps.setString(4, shuzu[3].trim());
				   ps.setString(5, shuzu[4].trim());
				   ps.setString(6, time);
			       ps.addBatch();//加入批处理
			       //河道防洪指标表
			       ps2.setString(1, shuzu[0].trim());
				   ps2.setString(2, shuzu[5].trim());
				   ps2.setString(3, shuzu[1].trim());
				   ps2.addBatch();
			       
                              }
				 }
			   ps.executeBatch();
			   ps2.executeBatch();
		    } catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error("河道采集",e); 
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				log.error("河道采集",e); 
				e.printStackTrace();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				log.error("河道采集",e); 
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("河道采集",e); 
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("河道采集",e); 
				e.printStackTrace();
			}
			finally{
//				try {
//				//	Thread.sleep(10000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			  System.out.println("河道线程"+Thread.currentThread().getName());
			  System.out.println(con);
			  DataBaseTools.Colse(con);
			  System.out.println("河道线程"+Thread.currentThread().getName()+"结束");
			}
	  }
	  
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  new River().run();
		//River.saveRiver("D:/home/zengzb/river/20160222/2016-02-22-14.txt","2013-05-06");
	}

}
