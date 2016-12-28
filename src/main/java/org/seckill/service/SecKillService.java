package org.seckill.service;

import java.util.List;

import org.seckill.dto.ExecuteSecKill;
import org.seckill.dto.Exposer;
import org.seckill.entity.SecKill;
import org.seckill.exceptions.RepeatKillException;
import org.seckill.exceptions.SecKillCloseException;
import org.seckill.exceptions.SecKillException;

/**
 * �ӿڵ���ƣ�Ҫע��վ��ʹ���ߵĽǶ�
 * �������棺1 �ӿڶ�������� 2 ���� 3 �������ͣ�return ����/�쳣��
 */
public interface SecKillService{

	/**
	 * ����������Ʒ�б�
	 * @return
	 */
	List <SecKill> getSecKillList();
	/**
	 * ���ص�����Ʒ
	 * @param secKillId
	 * @return
	 */
	SecKill getById(long secKillId);
	/**
	 * ��ɱ��ʼʱ��¶��ɱ��ַ��������ʾ��ɱ����ʱ
	 * @param secKillId
	 */
	Exposer exportSecKillUrl(long secKillId);
	
	/**
	 * 
	 * ִ����ɱ��Ķ���,���ܻ��׳����쳣���ظ���ɱ�쳣����ɱ�����쳣��һ����ɱ�쳣
	 * @param secKillId
	 * @param userPhone
	 * @param md5
	 */
	ExecuteSecKill executeSecKill(long secKillId,long userPhone,String md5)throws SecKillException,
	RepeatKillException,SecKillCloseException;
	
	/**
	 * //���ô洢����
	 * @param secKillId
	 * @param userPhone
	 * @param md5
	 * @return	 
	 */
	ExecuteSecKill executeSecKillProcedure(long secKillId,long userPhone,String md5);
}
