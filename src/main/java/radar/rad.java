package radar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.ssxt.common.tools.DataBaseTools;

public class rad {
    
	
	   public  static void main(String [] args){
		   String shu[]=new String[2];
		   String  txt;//一行数据
		   
		   try {
				 
			    InputStreamReader str = new InputStreamReader(new FileInputStream("D:/雷达/AREA.txt"),"UTF-8");
			    BufferedReader br = new BufferedReader(str);
		  
				while((txt = br.readLine())!=null){//使用readLine方法，一次读一行
					 String sql="INSERT INTO AREA (Code,AreaName,ParentId) values (?,?,?)";
					  shu=txt.split(",");
					  String []parameters={shu[0],shu[1],"176"};
						 DataBaseTools.executeUpdate(sql, parameters);
					
				}
		   }catch (Exception e) {
					// TODO: handle exception
				}
	   }
}
