package org.seckill.exceptions;

public class SecKillException extends RuntimeException
{
public  SecKillException(String message)
	{
			super(message);
	}
public  SecKillException(String message,Throwable cause)
{
		super(message);
}
}
