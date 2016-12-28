package org.seckill.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.seckill.dao.SecKillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dao.cache.RedisDao;
import org.seckill.dto.ExecuteSecKill;
import org.seckill.dto.Exposer;
import org.seckill.entity.SecKill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SecKillStateEnum;
import org.seckill.exceptions.RepeatKillException;
import org.seckill.exceptions.SecKillCloseException;
import org.seckill.exceptions.SecKillException;
import org.seckill.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
@Service
public class SecKillServiceImpl implements SecKillService
{
	private String salt="fdsfjjf*&*(&3429(_)$";
	@Autowired
	private SecKillDao secKillDao;
	@Autowired
	private RedisDao redisDao;
	@Autowired
	private SuccessKilledDao successKilledDao;
	private Logger logger=LoggerFactory.getLogger(this.getClass()); 

	public List<SecKill> getSecKillList()
	{
		List<SecKill> list=secKillDao.queryAll(0, 4);
		// TODO Auto-generated method stub
		return list;
	}

	public SecKill getById(long secKillId)
	{
		// TODO Auto-generated method stub
		SecKill secKill=secKillDao.queryById(secKillId);
		return secKill;
	}

	public Exposer exportSecKillUrl(long secKillId)
	{
		//先访问redis
		SecKill secKill;
		secKill=redisDao.getSecKill(secKillId);
		if(secKill==null)
		{	
			//访问mysql数据库
			secKill=secKillDao.queryById(secKillId);				
				if(secKill==null)
				{						
					return new Exposer(false,secKillId);
				}
				else
				{
					redisDao.putSecKill(secKill);
				}
		}
		
		Date startTime=secKill.getStart_time();
		Date endTime=secKill.getEnd_time();
		Date nowTime=new Date();
		if(nowTime.getTime()<startTime.getTime()||nowTime.getTime()>endTime.getTime())
		{
			return new Exposer(false,secKillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
		}
		String md5=getMD5(secKillId);
		return new Exposer(true,md5,secKillId);
		// TODO Auto-generated method stub
	}
	private String getMD5(long secKillId)
	{
		String base=salt+"/"+secKillId;
		String md5=DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
	/**
	 * 使用注解方式声明事务的有点
	 * 1、团队约定，注解一致的编程风格
	 * 2、保证事务的执行时间尽量短，不要执行网络，数据库和缓存操作，，不得已话从事务中剥离出来
	 * 3、不是所有的事件都需要事务。
	 */
	@Transactional
	public ExecuteSecKill executeSecKill(long secKillId, long userPhone,
			String md5)
			throws SecKillException, RepeatKillException, SecKillCloseException
	{	
		if(md5==null||!(getMD5(secKillId).equals(md5)))
		{			
			throw new SecKillException("seckill data rewrite!");
		}		
		Date killTime=new Date();	
		try{
			int insertCount=successKilledDao.insertSucessKilled(secKillId, userPhone);
			if(insertCount<=0)
			{ 				
				throw new RepeatKillException("repeat kill");
			}
			else
			{		
				int updateCount=secKillDao.reduceNumber(secKillId, killTime);			
				if(updateCount<=0)
				{
					throw new SecKillCloseException("secKill is closed");
				}
				else{									
					
				}
				SuccessKilled successKilled=successKilledDao.queryByIdWithSecKill(secKillId, userPhone);						
				return new ExecuteSecKill(secKillId,SecKillStateEnum.SUCCESS,successKilled);
			}		
		}
		catch(RepeatKillException re){
			throw re;
		}
		catch(SecKillCloseException sce){
			throw sce;
		}
		catch(Exception ex)
		{
			throw new SecKillException("seckill inner error:"+ex.getMessage());
		}
		
	}	
	public ExecuteSecKill executeSecKillProcedure(long secKillId,long userPhone,String md5)
	{
		if(md5==null||!(getMD5(secKillId).equals(md5)))
		{
			return new ExecuteSecKill(secKillId,SecKillStateEnum.DATA_REWRITE);			
		}
		Date killTime=new Date();
		Map<String,Object>paramMap=new HashMap<String,Object>();
		//给map赋值
		System.out.println(userPhone);
		paramMap.put("secKillId", secKillId);
		paramMap.put("phone", userPhone);
		paramMap.put("killTime", killTime);
		paramMap.put("result", null);
		//执行存储过
		try{
		
			secKillDao.killByProcedure(paramMap);
			int result=MapUtils.getInteger(paramMap, "result",-2);//-2表示默认值
			if(result==1)
			{
				//
				
				SuccessKilled successKilled=successKilledDao.queryByIdWithSecKill(secKillId, userPhone);
				System.out.println(successKilled.getState());
				return new ExecuteSecKill(secKillId,SecKillStateEnum.SUCCESS,successKilled);
			}
			else {
				return new ExecuteSecKill(secKillId,SecKillStateEnum.stateOf(result));
			}
		}catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
			return new ExecuteSecKill(secKillId,SecKillStateEnum.INNER_ERROR);
		}		
	};


}
