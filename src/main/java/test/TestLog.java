
package test;
 
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
public class TestLog {
    public static void main(String[] args) {
     
       Logger log = Logger.getLogger(TestLog.class);
        log.setLevel(Level.DEBUG); //设置了这个log的level为INFO,而在配置文件中的//配的是log4j.logger.log4j=WARN只有这样if中的日志信息才能打印出来
       log.debug("DWD");;
//       System.out.println(log.getLevel());
       if (log.isInfoEnabled()) {
           log.warn("this is warn");
           log.info("this is info");
           log.error("this is error");
            log.debug("this is error");
       }
 
    	StringBuffer sql=new StringBuffer("WHERE stcd IN ('81203000', '81600800',) ");
    	sql.substring(sql.lastIndexOf(",")-1,sql.lastIndexOf(","));
    	System.out.println(sql);
     
    }
}