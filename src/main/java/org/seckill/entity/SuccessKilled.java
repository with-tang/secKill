package org.seckill.entity;

import java.util.Date;

public class SuccessKilled
{
	private long secKillId;
	private Date create_time;
	private short state;
	//多对一复合属性
	private SecKill secKill;
	public SecKill getSecKill()
	{
		return secKill;
	}
	public void setSecKill(SecKill secKill)
	{
		this.secKill = secKill;
	}
	public long getSecKillId()
	{
		return secKillId;
	}
	public void setSecKillId(long secKillId)
	{
		this.secKillId = secKillId;
	}
	public Date getCreate_time()
	{
		return create_time;
	}
	public void setCreate_time(Date create_time)
	{
		this.create_time = create_time;
	}
	public short getState()
	{
		return state;
	}
	public void setState(short state)
	{
		this.state = state;
	}
	@Override
	public String toString()
	{
		return "SuccessKilled [secKillId=" + secKillId
				+ ", create_time=" + create_time + ", state=" + state + "]";
	}
	
}
