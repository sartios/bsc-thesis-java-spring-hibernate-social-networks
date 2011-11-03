package com.sones.utilities;

import org.apache.log4j.Logger;

public class SonesLogger 
{
	private	static	Logger	_logger	=	null;
	
	public	SonesLogger( final Class clazz )
	{
		_logger	=	Logger.getLogger( clazz );
	}
	
	public	static	void	Error( final String message )
	{
		_logger.error( message );
	}
	
}
