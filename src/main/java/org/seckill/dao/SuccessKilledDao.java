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
 * ���뵽��ɱ�����ϸ��
 * @param secKillId
 * @param userPhone
 * @return  �������ֵ>1����ʾ���³ɹ�����������
 */
	public int insertSucessKilled(@Param("secKillId") long secKillId,@Param("userPhone")long userPhone);
	/**
	 *����id��ѯ��ɱ��Ʒ��ϸ�Ͷ���ʵ��
	 * @param secKillId
	 * @return
	 */
	public SuccessKilled queryByIdWithSecKill(@Param("secKillId")long secKillId,@Param("userPhone")long userPhone);
	
}
