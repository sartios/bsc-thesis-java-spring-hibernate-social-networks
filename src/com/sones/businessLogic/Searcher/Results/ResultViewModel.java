package com.sones.businessLogic.Searcher.Results;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ResultViewModel	implements	IResultViewModel
{

	private	String	_content;
	private	String	_creationDate;
	private	String	_creatorID;
	private	List<String>	_keywords;
	private	String	_type;
	private	String	_feedID;
	
	public	ResultViewModel()
	{
		_keywords	=	new	ArrayList<String>();
	}
	
	public	void	SetContent(String content)
	{
		_content	=	content;
	}
	
	@Override
	public String GetContent()
	{
		return	_content;
	}

	public	void	SetCreationDate(String creationDate)
	{
		_creationDate	=	creationDate;
	}
	
	@Override
	public String GetCreationDate() 
	{
		return	_creationDate;
	}

	public	void	SetCreator( String creatorID )
	{
		_creatorID	=	creatorID;
	}
	
	@Override
	public String GetCreator() 
	{
		return	_creatorID;
	}

	@Override
	public List<String> GetKeywords() 
	{
		return	_keywords;
	}

	public	void	SetType( String type )
	{
		_type	=	type;
	}
	
	@Override
	public String GetType()
	{
		return	_type;
	}

	public	void	SetFeedID( String feedID )
	{
		_feedID	=	feedID;
	}
	
	@Override
	public String GetFeedID() 
	{
		return	_feedID;
	}

	public void AddKeyword(String keyword) 
	{
		_keywords.add(keyword);
	}
	
	public	boolean	equals( Object o )
	{
		if( o instanceof ResultViewModel)
		{
			if( this._feedID == ((ResultViewModel)o).GetFeedID())
			{
				return	true;
			}
		}
		return	false;
	}
	
	public	int	hashCode()
	{
		return	_feedID.hashCode();
	}

	@Override
	public void AddKeyword(Collection<String> keywords)
	{
		_keywords.addAll(keywords);
	}

}
