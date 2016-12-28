package org.seckill.enums;

public enum SecKillStateEnum
{
	SUCCESS(0,"��ɱ�ɹ���"),
	END(1,"��ɱ������"),
	REPEAT_KILL(-1,"�ظ���ɱ��"),
	INNER_ERROR(-2,"ϵͳ�쳣��"),
	DATA_REWRITE(-3,"���ݴ۸ģ�")	
	;
	private int state;
	private String stateInfo;
	public int getState()
	{
		return state;
	}
	public String getStateInfo()
	{
		return stateInfo;
	}
	 SecKillStateEnum(int state, String stateInfo)
	{
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public static SecKillStateEnum stateOf(int index)
	{
		for(SecKillStateEnum state:values())
		{
			if(state.getState()==index)
			{
				return state;
			}
		}
		return null;
	}
}
