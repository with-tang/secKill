package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.cache.RedisDao;
import org.seckill.entity.SecKill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/spring-dao.xml"})
public class RedisDaoTest
{	
	private long secKillId=1001L;
	@Autowired
	private RedisDao redisDao;
	@Autowired
	private SecKillDao secKillDao;
	@Test
	public void redisDaoTest() throws Exception
	{
		SecKill secKill=redisDao.getSecKill(secKillId);
		 if(secKill==null)
		 {
			 secKill =secKillDao.queryById(secKillId);
			 if(secKill!=null)
			 {
				String result= redisDao.putSecKill(secKill);
				System.out.println(result);
				secKill=redisDao.getSecKill(secKillId);
				System.out.println(secKill);
			 }
		 }
		
	}
}
