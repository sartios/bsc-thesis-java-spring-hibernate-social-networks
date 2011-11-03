package com.sones.businessLogic.KeywordManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Keywords	implements	IKeywords
{
	private	List< IKeyword >	_keywords;
	
	public	Keywords()
	{
		_keywords	=	new	ArrayList<IKeyword>();
	}
	
	@Override
	public void Add(IKeyword keyword) 
	{
		_keywords.add( keyword );
	}

	@Override
	public Iterator<IKeyword> GetIterator() 
	{
		return	_keywords.iterator();
	}

}
