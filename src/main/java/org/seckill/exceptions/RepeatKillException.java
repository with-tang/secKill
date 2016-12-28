package org.seckill.exceptions;

public class RepeatKillException extends SecKillException
{
	public RepeatKillException(String message)
	{
		super(message);
	}
	public RepeatKillException(String message ,Throwable cause)
	{
		super(message,cause);
	}
	
}
