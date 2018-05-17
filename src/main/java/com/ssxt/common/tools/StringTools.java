package com.ssxt.common.tools;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringTools {
   public static String convertUTF8(String txt) throws Exception{
	   
	   String string = null;
	try {
		string = new String(txt.getBytes("iso-8859-1"),"utf-8");

	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "null";
	}
	return string;
	   
   }
   
   

	/** 转换为float数据类型的方法 **/
	public static float _Float(Object str) {
		float f = 0.0f;
		try {
			f = Float.parseFloat(str.toString());
			return f;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/** 转换为int数据类型的方法 **/
	public static int _Integer(Object str) {
		int i = 0;
		try {
			i = Integer.parseInt(str.toString());
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	/**
	 *String 转化为double
	 */
	public static double convertDouble (Object str){
		double num1 = 0.0;
		try {
			

		    num1 = Double.valueOf(str.toString());
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			return 0.0;
		} 
		return num1;
	}
	
	/**
	 *String 转化为double
	 */
	public static double convert_Double (Object str){
		double num1 = 0.0;
		try {
			

		    num1 = Double.valueOf(str.toString());
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			return -1;
		} 
		return num1;
	}
	/**
	 * 转换为double数据类型的方法,并且保留n为小数点 
	 */
	public static double _double(int n,Object str){
		double i = 0.0;
		try {
			i = Double.parseDouble(str.toString());
			BigDecimal bg = new BigDecimal(i);
			i = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			return i;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0.0;
		} 
	}
	
	/** 
	* 获得指定日期的后一天 
	* @param specifiedDay 
	* @return 
	*/ 
	public static String getSpecifiedDayAfter(String specifiedDay){ 
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar c = Calendar.getInstance(); 
		Date date=null; 
		try { 
		date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay); 
		} catch (ParseException e) { 
		e.printStackTrace(); 
		} 
		c.setTime(date); 
		int day=c.get(Calendar.DATE); 
		c.set(Calendar.DATE,day+1); 

		String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
		return dayBefore; 
		} 
	
	
	
	 /**
	  * 
	  * @param specifiedDay
	  * @param format 格式化
	  * @param dayNum 几天
	  * @return
	  */
	public static String getSpecifiedDayAfter(String specifiedDay,String format,int dayNum){ 
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar c = Calendar.getInstance(); 
		Date date=null; 
		try { 
		date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay); 
		} catch (ParseException e) { 
		e.printStackTrace(); 
		} 
		c.setTime(date); 
		int day=c.get(Calendar.DATE); 
		c.set(Calendar.DATE,day+dayNum); 

		String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
		return dayBefore; 
		} 
	
	/** 
	* 获得指定日期的后几天 
	* @param specifiedDay 
	* @return 
	*/ 
	public static String getBeforeDay(String specifiedDay,int dayNum){ 
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar c = Calendar.getInstance(); 
		Date date=null; 
		try { 
		date = new SimpleDateFormat("yyyyMMdd").parse(specifiedDay); 
		} catch (ParseException e) { 
		e.printStackTrace(); 
		} 
		c.setTime(date); 
		int day=c.get(Calendar.DATE); 
		c.set(Calendar.DATE,day-dayNum); 

		String dayBefore=new SimpleDateFormat("yyyyMMdd").format(c.getTime()); 
		return dayBefore; 
		} 
	
	 
	public static String gettest(String specifiedDay){ 
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar c = Calendar.getInstance(); 
		Date date=null; 
		try { 
		date = new SimpleDateFormat("yyyyMMddHH:mm:ss").parse(specifiedDay); 
		} catch (ParseException e) { 
		e.printStackTrace(); 
		} 
		c.setTime(date); 
	    c.add(Calendar.MINUTE, 5);

		String dayBefore=new SimpleDateFormat("yyyyMMddHH:mm:ss").format(c.getTime()); 
		return dayBefore; 
		} 
	
	
	 /**
	   * 在日期上加分钟数
	   * @param day 日期
	   * @param x 分钟数
	   * @return
	   */
	  public static String addMin(String day, int x)
	    {
	        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");//24小时制
	        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制
	        Date date = null;
	        try
	        {
	            date = format.parse(day);
	        }
	        catch (Exception ex)   
	        {
	            ex.printStackTrace();
	        }
	        if (date == null) return "";
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.MINUTE, x);//24小时制
	        //cal.add(Calendar.HOUR, x);12小时制
	        date = cal.getTime();
	      //  System.out.println("front:" + date);
	        cal = null;
	        return format.format(date);
	    } 
	  
	  /**
	   * 在日期上加小时
	   * @param day 日期
	   * @param x 小时
	   * @return
	   */
	  public static String addHour(String day, int x)
	    {
	        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");//24小时制
	        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制
	        Date date = null;
	        try
	        {
	            date = format.parse(day);
	        }
	        catch (Exception ex)   
	        {
	            ex.printStackTrace();
	        }
	        if (date == null) return "";
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	      // cal.add(Calendar.MINUTE, x);//24小时制
	         cal.add(Calendar.HOUR, x);//12小时制
	        date = cal.getTime();
	      //  System.out.println("front:" + date);
	        cal = null;
	        return format.format(date);
	    } 
}
