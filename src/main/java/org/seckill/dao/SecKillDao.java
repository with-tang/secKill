package org.seckill.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SecKill;
/**
 * 
 * @author tang
 *
 */
public interface SecKillDao {

	/**
	 * reduce product number
	 * @param seckillId
	 * @param killTime
	 * @return if effect line number >=1 ,means update lines. if return 0 means reduce fail
	 */
	int reduceNumber(@Param("secKillId") long secKillId,@Param("killTime") Date killTime);
	//int reduceNumber(long secKillId,Date killTime);
	/**
	 * query seckill product
	 * @param seckillId
	 * @return
	 */
	SecKill queryById(long secKillId);

	/**
	 * query products from seckill
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<SecKill> queryAll(@Param("offset")int offset,@Param("limit")int limit);
	//List<SecKill> queryAll(int offset,int limit);
	
	void killByProcedure(Map<String,Object> paramMap);
}

