package test;

 
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
 import org.springframework.transaction.annotation.Transactional;

import com.ssxt.dao.RiverDao;
import com.ssxt.service.RiverSerivce;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"/applicationContext.xml"})
 @Transactional
public class SpringTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	RiverSerivce riverSerivce;
		
		  @Test
		  public void findAll()
		    {
			  String [] a={"01500400","10401410"};
			List list=  riverSerivce.findWadiWaterInfo(a, "广东");
			System.out.println(list.size());
		   }
	   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			  
	}

}
