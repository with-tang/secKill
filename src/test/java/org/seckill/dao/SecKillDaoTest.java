package org.seckill.dao;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SecKill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/spring-dao.xml"})
public class SecKillDaoTest
{
	//注入DAO实现类依赖
	@Resource	
	private SecKillDao secKillDao;
	
	@Test
	public void testReduceNumber()throws Exception
	{
		Date killTime=new Date();
		long l=1001;
		int updateCount=secKillDao.reduceNumber(l,killTime);
		System.out.println("updateCount=:"+updateCount);
	}

	@Test
	public void testQueryById()throws Exception
	{
		long id=1000;
		SecKill secKill=secKillDao.queryById(id);
		System.out.println(secKill.getName());
		System.out.println(secKill);
	}

	@Test
	public void testQueryAll()throws Exception
	{
		List <SecKill> seckills=secKillDao.queryAll(0, 4);
		for(SecKill seckill:seckills)
		{
			System.out.println(seckill);
		}
	}

}
