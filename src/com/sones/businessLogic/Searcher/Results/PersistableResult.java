package com.sones.businessLogic.Searcher.Results;

import com.sones.dao.Searcher.Results.IPersistableResult;

public class PersistableResult implements	IResult, IPersistableResult
{
	private	String	_feedID;
	private	String	_keyword;
	private	String	_userID;
	

	public	PersistableResult()
	{
	}
	
	
	public void set_feedID(String feedID) 
	{
		if( IsNull( feedID ) )
		{
			_feedID = feedID;
		}
	}
	
	public	String	get_feedID()
	{
		return	_feedID;
	}
	
	public void set_keyword(String keyword) 
	{
		if( IsNull( keyword ) )
		{
			_keyword = keyword;
		}
	}
	
	public	String	get_keyword()
	{
		return	_keyword;
	}
	
	public void set_userID(String userID) 
	{
		if( IsNull( userID ) )
		{
			_userID = userID;
		}
	}
	
	public	String	get_userID()
	{
		return	_keyword;
	}
	
	
	public	String	GetKeywordID()
	{
		return	_keyword;
	}
	
	@Override
	public String GetFeedID() 
	{
		return	get_feedID();
	}

	@Override
	public String GetKeyword()
	{
		return	GetKeywordID();
	}

	@Override
	public void SetFeedID(String feedID) 
	{
		if( IsNull( feedID ) )
		{
			_feedID	=	feedID;
		}
	}
	
	@Override
	public void SetKeyword( final String keyword ) 
	{
		if( IsNull( keyword ) )
		{
			_keyword	=	keyword;
		}
	}
	
	public void SetUserID(String userID) 
	{
		if( IsNull( userID ) )
		{
			_userID = userID;
		}
	}
	
	public String GetUserID() 
	{
		return _userID;
	}
	
	private boolean IsNull( final Object object )
	{
		if( null == object )
		{
			return	false;
		}
		return	true;
	}
	


}
