package org.seckill.dto;

public class Exposer
{
	private boolean exposed;
	private String md5;
	
	private  long secKillId;
	
	private long now;
	
	private long start;
	
	private long end;
	public Exposer(boolean exposed, String md5, long secKillId)
	{
		this.exposed = exposed;
		this.md5 = md5;
		this.secKillId = secKillId;
	}

	public boolean isExposed()
	{
		return exposed;
	}

	public void setExposed(boolean exposed)
	{
		this.exposed = exposed;
	}

	public String getMd5()
	{
		return md5;
	}

	public void setMd5(String md5)
	{
		this.md5 = md5;
	}

	public long getSecKillId()
	{
		return secKillId;
	}

	public void setSecKillId(long secKillId)
	{
		this.secKillId = secKillId;
	}

	public long getNow()
	{
		return now;
	}

	public void setNow(long now)
	{
		this.now = now;
	}

	public long getStart()
	{
		return start;
	}

	public void setStart(long start)
	{
		this.start = start;
	}

	public long getEnd()
	{
		return end;
	}

	public void setEnd(long end)
	{
		this.end = end;
	}

	public Exposer(boolean exposed, long secKillId,long now, long start, long end)
	{
		this.secKillId=secKillId;
		this.exposed = exposed;
		this.now = now;
		this.start = start;
		this.end = end;
	}

	@Override
	public String toString()
	{
		return "Exposer [exposed=" + exposed + ", md5=" + md5 + ", secKillId="
				+ secKillId + ", now=" + now + ", start=" + start + ", end="
				+ end + "]";
	}

	public Exposer(boolean exposed, long secKillId)
	{
		this.exposed = exposed;
		this.secKillId = secKillId;
	}
}
