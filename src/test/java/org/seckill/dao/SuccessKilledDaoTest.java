package org.seckill.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/spring-dao.xml"})
public class SuccessKilledDaoTest
{
	@Resource
	private SuccessKilledDao successKilledDao;
	@Test
	public void insertSucessKilled()
	{
		int insertCount=successKilledDao.insertSucessKilled(1000L, 15926270103L);
		System.out.println(insertCount);		
	}

	@Test
	public void queryByIdWithSecKill()
	{
		SuccessKilled successKilled =successKilledDao.queryByIdWithSecKill(1000L, 15926270102L);
		System.out.println(successKilled);		
	}
}
