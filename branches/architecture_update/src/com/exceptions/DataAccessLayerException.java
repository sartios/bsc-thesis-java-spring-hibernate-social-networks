package com.exceptions;

public class DataAccessLayerException extends RuntimeException{

	public DataAccessLayerException(Throwable cause){
		super(cause);
	}
}