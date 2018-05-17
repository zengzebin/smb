package test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread  extends Thread{

	int number;

	public MyThread(int num) {

	number = num;

	System.out.println("创建线程 " + number);

	}

	public void run() {
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String time= df.format(new Date());// new Date()为获取当前系统时间
      while(true) {
     System.out.println("线程 " + number + ":时间 " + time);
	 
	  try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
     }
      
	}
	public static void main(String args[]) {
     
 
	  new MyThread(1).start();
	   
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new MyThread(2).start();
 
	}

	}

 
