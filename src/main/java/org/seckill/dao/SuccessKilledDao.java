package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 * 
 * @author tang
 *
 */
public interface SuccessKilledDao
{
/**
 * 插入到秒杀结果明细表
 * @param secKillId
 * @param userPhone
 * @return  如果返回值>1，表示更新成功及更新行数
 */
	public int insertSucessKilled(@Param("secKillId") long secKillId,@Param("userPhone")long userPhone);
	/**
	 *根据id查询秒杀商品明细和对象实体
	 * @param secKillId
	 * @return
	 */
	public SuccessKilled queryByIdWithSecKill(@Param("secKillId")long secKillId,@Param("userPhone")long userPhone);
	
}
