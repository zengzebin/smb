package com.ssxt.common.tools;

 
 
 import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 


public class DataBaseTools {
	    private Connection conn = null;
	    private PreparedStatement ps = null;
	    private ResultSet rs = null;
	     
	 public  static  Connection getCon(){
		  Connection conn = null;
	        String sql;
	        // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称： 
	        // 避免中文乱码要指定useUnicode和characterEncoding
	        // 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己 
	        // 下面语句之前就要先创建javademo数据 
	     /*   String url = "jdbc:mysql://127.0.0.1:3306/rainwater?"
	                + "user=rainwater&password=zzb11231l11l&useUnicode=true&characterEncoding=UTF8";*/
          String url = "jdbc:mysql://127.0.0.1:3306/rainwater?"
 	                + "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
 	 
	           // 之所以要使用下面这条语句，是因为要使用MySQL的驱动 以我们要把它驱动起来 
	            // 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
	            try {
					Class.forName(ConstParam.getKey("jdbc.driverClassName"));
				      conn =  DriverManager.getConnection(ConstParam.getKey("jdbc.url"), ConstParam.getKey("jdbc.username"), ConstParam.getKey("jdbc.password"));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();}//  
	            
				return conn;
	         
	 }
	 
	 public  static  void Colse(Connection con){
		 try {
			 con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	    //该方法执行一个update/delete/insert语句
	    //sql语句是带问号的格式，如：update table_name set column_name = ? where ...
	    //parameters = {"...", "..."...}；
	 public static void executeUpdate(String sql, String[] parameters) {
		  Connection  conn=null;
         try {
              conn =  getCon();
           PreparedStatement    ps = conn.prepareStatement(sql);
             //给？赋值
             if (parameters != null) {
                 for (int i=0; i<parameters.length; i++) {
                     ps.setString(i+1, parameters[i]);
                 }
             }
             //执行语句
             ps.executeUpdate();
             ps.close();
         } catch (SQLException e) {
             e.printStackTrace();
             throw new RuntimeException(e.getMessage());
         } finally {
             //关闭资源
        	 Colse(conn);
         }
 }
	 
	 
	   //统一的select语句，为了能够访问结果集，将结果集放入ArrayList，这样可以直接关闭资源
	    public    List executeQuery(String sql, String[] parameters) {
	        List results = new ArrayList();
	     
	        try {
	        	   conn =  getCon();
	            ps = conn.prepareStatement(sql);
	             
	            if (parameters != null) {
	                for (int i=0; i<parameters.length; i++) {
	                    ps.setString(i+1, parameters[i]);
	                }
	            }
	             
	            rs = ps.executeQuery();
	             
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int column = rsmd.getColumnCount();
	             
	            while (rs.next()) {
	                Object[] objects = new Object[column];
	                 
	                for (int i=1; i<=column; i++) {
	                    objects[i-1] = rs.getObject(i);
	                }
	                 
	                results.add(objects);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        } finally {
	        	 Colse(conn);
	        }
	        return results; 
	    }
	     
	 
	public static void main(String[] args) {
	 
		
 	}

}
