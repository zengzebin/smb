package com.ssxt.common.tools;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssxt.model.AdminCode;
import com.ssxt.model.City;
import com.ssxt.model.Province;
import com.ssxt.vo.Boundary.CityBoundary;
import com.ssxt.vo.Boundary.ProvinceBoundary;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FileTools {
	/**
	 * 获取省份的轮廓
	 * @param fileName 文件路径
	 * @param name 省名
	 * @return 返回json
	 */
   public static  String findProvince(String  fileName,String name){
	   /* String filePath=fileName+"WEB-INF/sunkingWater/provinceBoundyDatas/"+name+".txt";
	     System.out.println("查找的省份："+name+"路径="+filePath);   
	      String result=new String();
	      JSONObject jsonObject=new JSONObject();
	      int error=0;
	    try {
           String encoding="GBK";
           File file=new File(filePath);
           if(file.isFile() && file.exists()){ //判断文件是否存在
               InputStreamReader read = new InputStreamReader(
               new FileInputStream(file),encoding);//考虑到编码格式
               BufferedReader bufferedReader = new BufferedReader(read);
               String s =new String();;
               while((s = bufferedReader.readLine()) != null){
            	   result+=s;  
               }
                read.close();
    }else{
    	 error=1;
       System.out.println("找不到指定的文件");
      
   }
   } catch (Exception e) {
	   error=1;
       System.out.println("读取文件内容出错");
       e.printStackTrace();
   }
	 
        Map<String,Object> map =new HashMap<String,Object>();
        if(error!=1){
        //List<City> list=document(fileName,name);
        Province p=new Province();
        p.setProvinceBoundary(result);
        p.setProvinceName(name);
        System.out.println(p.getProvinceBoundary());
        map.put("error", error);
      //  map.put("cityList", list);
        map.put("province", p);
        }else{
        map.put("error", error);
        }
        jsonObject = JSONObject.fromObject(map);
	    return jsonObject.toString();*/
	   return null;
}
   
   
   
      public static byte[] readInputStream(InputStream inStream) throws Exception{  
       ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
       //创建一个Buffer字符串  
       byte[] buffer = new byte[1024];  
       //每次读取的字符串长度，如果为-1，代表全部读取完毕  
       int len = 0;  
       //使用一个输入流从buffer里把数据读取出来  
       while( (len=inStream.read(buffer)) != -1 ){  
           //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
           outStream.write(buffer, 0, len);  
       }  
       //关闭输入流  
       inStream.close();  
       //把outStream里的数据写入内存  
       return outStream.toByteArray();  
   }  

	   /**
	    * 
	    * @param fileName 路径
	    * @param ProvinceName 查找的省份
	    * @return
	    */
     public  static List<CityBoundary> document(File  file){
    	 
    	/*  File[] tempList = file.listFiles();
    	  List <City>cityNames=new ArrayList<City>();
    	  System.out.println("该目录下对象个数："+tempList.length);
    	  for (int i = 0; i <tempList.length; i++) {
    	   if (tempList[i].isFile()) {
    	   System.out.println("文     件："+tempList[i]);
    	      
    	   }
    	  }*/
    	  
       return  null;
     }
     
     
     
     /**
      * 删除目录
      * @param dir
      * @return
      */
     public static boolean deleteDir(File dir) {
   	  
         if (dir.isDirectory()) {
             String[] children = dir.list();
             for (int i=0; i<children.length; i++) {
                 boolean success = deleteDir(new File(dir, children[i]));
                 if (!success) {
                     return false;
                 }
             }
         }
  
         // The directory is now empty so now it can be smoked
         return dir.delete();
     }
     
     public static  boolean   mkdirFile(String path){
   	  File file =new File(path);
   	  if  (!file .exists()  && !file .isDirectory())      
	     {       
	         System.out.println("//不存在");  
	         file.mkdirs();  
	         System.out.println("创建文件夹"+path);  
	         return true;
	     }else{
	    	   return false;
	     }
     }
    
     /** 
      * 删除单个文件 
      *  
      * @param fileName 
      *            要删除的文件的文件名 
      * @return 单个文件删除成功返回true，否则返回false 
      */  
     public static boolean deleteFile(String fileName) {  
      File file = new File(fileName);  
      // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除  
      if (file.exists() && file.isFile()) {  
       if (file.delete()) {  
        System.out.println("删除单个文件" + fileName + "成功！");  
        return true;  
       } else {  
        System.out.println("删除单个文件" + fileName + "失败！");  
        return false;  
       }  
      } else {  
       System.out.println("删除单个文件失败：" + fileName + "不存在！");  
       return false;  
      }  
     } 
     
     
      /**
       * 
       * @param path 文件路径
       * @param Province 查找的省
       * @param City 查找的市
       * @return 返回是code
       */
      public static List<AdminCode> FileCode(String path,String Province,String City){
    	  
     //  String filePaht=path+"WEB-INF/AdminCode/"+Province+".txt";
       String filePaht="d:\\AdminCode\\"+Province+".txt";
      
        List <AdminCode>code=new ArrayList<AdminCode>();
	    try {
          String encoding="UTF-8";
          
          File file=new File(filePaht);
          if(file.isFile() && file.exists()){ //判断文件是否存在
              InputStreamReader read = new InputStreamReader(
              new FileInputStream(file),encoding);//考虑到编码格式
              BufferedReader bufferedReader = new BufferedReader(read);
              String s = new String();
              int num=0;
              while((s = bufferedReader.readLine()) != null){
               String  txt[]=s.split(",");
                  if(txt[1].startsWith(City)){
                	  AdminCode admin=new AdminCode();
                	  admin.setCityName(City);
                	  admin.setCode(txt[0]);
                	  code.add(admin);
                	  
                  }
            	 
              }
             read.close();
             
       
              
  }else{
      System.out.println("找不到指定的文件");
  }
  } catch (Exception e) {
      System.out.println("读取文件内容出错");
      e.printStackTrace();
  }
       
		return code;
    	  
      }
      
   
     
        /**
         * 返回全国轮廓
         * @param path 路径
         * @return
         */
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
      
      
      /**
       * 返回省级下的所有城市轮廓
       * @param path 路径
       * @return
       */
       public static CityBoundary  findCity(String path){
        	 String cityName = path.substring(path.lastIndexOf(System.getProperty("file.separator")) + 1,path.lastIndexOf('.') ); 
        	 CityBoundary c=new CityBoundary();
 	      String points = new String();
 	    try {
            String encoding="GBK";
            File file=new File(path);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String s = new String();
                while((s = bufferedReader.readLine()) != null){
             	   points+=s;  
                }
               read.close();
               c.setCityName(cityName);
               c.setPoints(points);
                
 	   }else{
 	       System.out.println("找不到指定的文件");
 	   }
 	   } catch (Exception e) {
 	       System.out.println("读取文件内容出错");
 	       e.printStackTrace();
 	   }
 	    return c;
       }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file=new File("D:"+System.getProperty("file.separator") +"sunkingWater"+
				System.getProperty("file.separator") +"cityBoundyDatas"+System.getProperty("file.separator")+"广东");
   	   File[] tempList = file.listFiles();
	  List<CityBoundary> infoList=new ArrayList<CityBoundary>();
    	  System.out.println("该目录下对象个数："+tempList.length);
   	   for (int i = 0; i <tempList.length; i++) {
   	   if (tempList[i].isFile()) {
 		String path=tempList[i].toString();
		CityBoundary c=findCity(path);
		infoList.add(c);
   	   }
   	  }
	}
}
 