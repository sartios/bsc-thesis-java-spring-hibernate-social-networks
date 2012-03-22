package com.sones.dao.exception;

public class DataAccessLayerException extends RuntimeException{

	public DataAccessLayerException(Throwable cause){
		super(cause);
	}
	
	public DataAccessLayerException(String message)
	{
		super(message);
	}
}