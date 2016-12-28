package org.seckill.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.ExecuteSecKill;
import org.seckill.dto.Exposer;
import org.seckill.entity.SecKill;
import org.seckill.exceptions.RepeatKillException;
import org.seckill.exceptions.SecKillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 接口的设计，要注意站在使用者的角度
 * 三个方面：1 接口定义的粒度 2 参数 3 返回类型（return 类型/异常）
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:spring/spring-dao.xml",
	"classpath:spring/spring-service.xml"
})

public class SecKillServiceTest{
	
	private final Logger logger=LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SecKillService secKillService;
	/**
	 * 返回所有商品列表
	 * @return
	 */
	@Test
	public void getSecKillList()throws Exception
	{
			List<SecKill> list=secKillService.getSecKillList();
			logger.info("list:{}"+list);
	}
	/**
	 * 返回单个商品
	 * @param secKillId
	 * @return
	 */
	@Test
	public void getById()throws Exception
	{
		SecKill secKill=secKillService.getById(1000L);
		logger.info("secKill={}"+secKill);
	}
	/**
	 * 秒杀开始时暴露秒杀地址，否则显示秒杀倒计时
	 * @param secKillId
	 *///测试代码完整逻辑，注意可重复执行
	/*@Test
	public void  exportSecKillUrl()throws Exception
	{
		long secKillId=1000L;
		Exposer exposer=secKillService.exportSecKillUrl(secKillId);
		if(exposer.isExposed())
		{
			logger.info("exposer={}",exposer);
			long phone=14432423456L;
			String md5=exposer.getMd5();
			try
			{
				ExecuteSecKill execution=secKillService.executeSecKill(secKillId, phone, md5);
				logger.info("result={}",execution);
			}
			catch(RepeatKillException re){logger.error(re.getMessage());}
			catch(SecKillCloseException se){logger.error(se.getMessage());}
			
			
		}else
		{
			logger.warn("exposer={}"+exposer);
		}
	}*/
	@Test
	public void testExecuteSecKillProcedure()throws Exception
	{
		long secKillId=1001;
		long phone=18842333866l;
		String md5=null;
		//Date killTime =new Date();
		Exposer exposer=secKillService.exportSecKillUrl(secKillId);
		if(exposer.isExposed()){
			md5=exposer.getMd5();
		}
		ExecuteSecKill executeSecKill= secKillService.executeSecKillProcedure(secKillId, phone, md5);
		logger.info(executeSecKill.getStateInfo());
		
	}
}
