package com.sones.businessLogic.KeywordManager;

public class Keyword	implements	IKeyword
{
	private	String	_id;
	private	String	_value;
	
	public	Keyword()
	{
	}

	public	String	get_id()
	{
		return	_id;
	}
	
	public	void	set_id( final String id )
	{
		_id	=	id;
	}
	
	@Override
	public String GetID() 
	{
		return	get_id();
	}

	@Override
	public String GetValue() 
	{
		return	_value;
	}

}
