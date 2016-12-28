package org.seckill.dto;

import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SecKillStateEnum;

public class ExecuteSecKill
{
	private long secKillId;
	private int state;
	private String stateInfo;
	private  SuccessKilled successKilled;
	public ExecuteSecKill(long secKillId, SecKillStateEnum states,SuccessKilled successKilled)
	{
		this.secKillId = secKillId;
		this.state = states.getState();
		this.stateInfo = states.getStateInfo();
		this.successKilled = successKilled;
	}
	public ExecuteSecKill(long secKillId, SecKillStateEnum states)
	{
		this.secKillId = secKillId;
		this.state = states.getState();
		this.stateInfo = states.getStateInfo();
	}
	public long getSecKillId()
	{
		return secKillId;
	}
	public void setSecKillId(long secKillId)
	{
		this.secKillId = secKillId;
	}
	public int getState()
	{
		return state;
	}
	public void setState(int state)
	{
		this.state = state;
	}
	public String getStateInfo()
	{
		return stateInfo;
	}
	public void setStateInfo(String stateInfo)
	{
		this.stateInfo = stateInfo;
	}
	public SuccessKilled getSuccessKilled()
	{
		return successKilled;
	}
	public void setSuccessKilled(SuccessKilled successKilled)
	{
		this.successKilled = successKilled;
	}
	
}
