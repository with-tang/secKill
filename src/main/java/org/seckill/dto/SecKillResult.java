package org.seckill.dto;

public class SecKillResult<T>
{
	private boolean success;
	public SecKillResult(boolean success, T data)
	{
		this.success = success;
		this.data = data;
	}
	private T data;
	private String  error;
	public SecKillResult(boolean success, String error)
	{
		this.success = success;
		this.error = error;
	}
	public boolean isSuccess()
	{
		return success;
	}
	public void setSuccess(boolean success)
	{
		this.success = success;
	}
	public T getData()
	{
		return data;
	}
	public void setData(T data)
	{
		this.data = data;
	}
	public String getError()
	{
		return error;
	}
	public void setError(String error)
	{
		this.error = error;
	} 
}
