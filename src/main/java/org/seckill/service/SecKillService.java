package org.seckill.service;

import java.util.List;

import org.seckill.dto.ExecuteSecKill;
import org.seckill.dto.Exposer;
import org.seckill.entity.SecKill;
import org.seckill.exceptions.RepeatKillException;
import org.seckill.exceptions.SecKillCloseException;
import org.seckill.exceptions.SecKillException;

/**
 * 接口的设计，要注意站在使用者的角度
 * 三个方面：1 接口定义的粒度 2 参数 3 返回类型（return 类型/异常）
 */
public interface SecKillService{

	/**
	 * 返回所有商品列表
	 * @return
	 */
	List <SecKill> getSecKillList();
	/**
	 * 返回单个商品
	 * @param secKillId
	 * @return
	 */
	SecKill getById(long secKillId);
	/**
	 * 秒杀开始时暴露秒杀地址，否则显示秒杀倒计时
	 * @param secKillId
	 */
	Exposer exportSecKillUrl(long secKillId);
	
	/**
	 * 
	 * 执行秒杀后的对象,可能会抛出的异常，重复秒杀异常，秒杀结束异常，一般秒杀异常
	 * @param secKillId
	 * @param userPhone
	 * @param md5
	 */
	ExecuteSecKill executeSecKill(long secKillId,long userPhone,String md5)throws SecKillException,
	RepeatKillException,SecKillCloseException;
	
	/**
	 * //调用存储过程
	 * @param secKillId
	 * @param userPhone
	 * @param md5
	 * @return	 
	 */
	ExecuteSecKill executeSecKillProcedure(long secKillId,long userPhone,String md5);
}
