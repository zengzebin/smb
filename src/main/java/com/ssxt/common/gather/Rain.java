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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.ssxt.common.tools.ConstParam;
import com.ssxt.common.tools.DataBaseTools;
import com.ssxt.common.tools.FileTools;
import com.ssxt.common.tools.MyExceptionHandler;
import com.ssxt.common.tools.StringTools;

public class Rain {

	private static Logger log = Logger.getLogger(Rain.class);

	public void run() {
		try {

			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH");// 可以方便地修改日期格式
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:00:00");// 记录信息
			SimpleDateFormat filetime = new SimpleDateFormat("yyyyMMdd");// 文件夹名称
			String time = dateFormat.format(now);
			System.out.println("雨晴采集开始时间为" + dateFormat1.format(now));

			String daypath = ConstParam.saveRainFile;
			WebClient webClient = new WebClient(BrowserVersion.CHROME);
			// 设置webClient的相关参数
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setCssEnabled(false);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			// 模拟浏览器打开一个目标网址
			HtmlPage rootPage = webClient.getPage("http://xxfb.hydroinfo.gov.cn/ssIndex.html?type=3");
			System.out.println("为了获取js执行的数据 线程开始沉睡等待");
			Thread.sleep(10000);// 主要是这个线程的等待 因为js加载也是需要时间的
			System.out.println("线程结束沉睡");
			List listdiv = rootPage.getByXPath("//div[@id='yltable']");
			HtmlDivision div = (HtmlDivision) listdiv.get(0);
			String html = div.asText();
			html = html.replaceAll("	", ",");
			FileTools.mkdirFile(daypath + filetime.format(now));// 创建文件夹
			/** * 写入文件 */
			String fileName = daypath + filetime.format(now) + "/" + time + ".txt";
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
			// writer.write(html);
			writer.write(html);
			writer.close();
			/** * **************/
			saveRain(fileName, dateFormat1.format(now));
			String beforeTime = StringTools.getBeforeDay(filetime.format(now), 7);
			System.out.println("删除文件夹" + daypath + beforeTime);
			File deleteFile = new File(daypath + beforeTime);
			FileTools.deleteDir(deleteFile);
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			log.error("采集雨情定时器", e);
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			log.error("采集雨情定时器", e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("采集雨情定时器", e);
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("采集雨情定时器", e);
			e.printStackTrace();
		}

	}

	public static void saveRain(String fileName, String time) {
		String txt;// 一行数据
		int num = 0;
		String rain;// 天气
		String shu[] = new String[4];
		String sql = "INSERT INTO ST_PPTN_R_NEW (BSNM, ADDVCD, RVNM,STMN,TM,DYP,WTH,addTime) values (?,?,?,?,?,?,?,?)";
		String count;
		PreparedStatement ps = null;// 插入
		Statement stmt;// 查询
		ResultSet rs = null;// 结果
		Connection con = null;
		try {
			File file = new File(fileName);
			con = DataBaseTools.getCon();// 获取jdbc连接
			ps = con.prepareStatement(sql);
			stmt = con.createStatement();
			InputStreamReader str = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
			BufferedReader br = new BufferedReader(str);

			while ((txt = br.readLine()) != null) {// 使用readLine方法，一次读一行
				// txt=txt.replace("\\t", "");
				shu = txt.split(",");
				count = "SELECT count(1)  FROM  ST_PPTN_R_NEW  WHERE TM='" + shu[4].trim() + " 00:00:00' AND STMN='"
						+ shu[3].trim() + "' AND BSNM='" + shu[0].trim() + "'";
				rs = stmt.executeQuery(count);
				rs.next();
				num = rs.getInt(1);// 是否已经有记录有返回1,没有返回0
				// if(num==1){
				// System.out.println(num);
				// }
				if (num == 0) {
					// num++;
					ps.setString(1, shu[0].trim());
					ps.setString(2, shu[1].trim());
					ps.setString(3, shu[2].trim());
					ps.setString(4, shu[3].trim());
					ps.setString(5, shu[4].trim());
					if (shu[5].trim().equals("--"))
						ps.setString(6, "0");
					else
						ps.setDouble(6, Double.parseDouble(shu[5].trim()));
					// System.out.println(shu[6]);
					rain = shu[6].toString().trim();
					ps.setString(7, "0");
					if (rain.equals("雪"))
						ps.setString(7, "5");
					if (rain.equals("雨夹雪"))
						ps.setString(7, "6");
					if (rain.equals("雨"))
						ps.setString(7, "7");
					if (rain.equals("阴"))
						ps.setString(7, "8");
					if (rain.equals("晴"))
						ps.setString(7, "9");
					ps.setString(8, time);
					ps.addBatch();// 加入批处理
				}
			}
			ps.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("采集雨情定时器", e);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error("采集雨情定时器", e);
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			log.error("采集雨情定时器", e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("采集雨情定时器", e);
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("采集雨情定时器", e);
			e.printStackTrace();
		} finally {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("河道线程" + Thread.currentThread().getName());
			System.out.println(con);
			DataBaseTools.Colse(con);
			System.out.println("河道线程" + Thread.currentThread().getName() + "结束");
		}
	}

	public static void main(String[] args) {
		new Rain().run();
		// RainTime r=new RainTime();
		// r.run();
		// Date now = new Date();
		//// SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd
		// HH:00:00");//可以方便地修改日期格式
		//// saveRain("D:/home/zengzb/rain/2016-02-01-13.txt",dateFormat1.format(now));
		//
		// Calendar calendar = Calendar.getInstance();
		// calendar.set(Calendar.HOUR_OF_DAY, 9); // 控制时
		// calendar.set(Calendar.MINUTE, 40); // 控制分
		// calendar.set(Calendar.SECOND, 00); // 控制秒
		// Timer timer = new Timer();
		// Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00
		// //间隔：1小时
		// //long period = 1000 * 60 * 60;
		// //测试时间每分钟一次
		// long period =1000 * 60 * 3;
		// System.out.println("=========================调用=========================");
		// RainTime t=new RainTime();
		// timer.schedule(t, time,period);
		// saveRain("d:/question.txt","ddd");

	}

}