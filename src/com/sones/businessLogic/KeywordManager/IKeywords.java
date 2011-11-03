package com.sones.businessLogic.KeywordManager;

import java.util.Iterator;

public interface IKeywords 
{
	public	Iterator< IKeyword >	GetIterator();
	public	void	Add( final IKeyword keyword );
}
