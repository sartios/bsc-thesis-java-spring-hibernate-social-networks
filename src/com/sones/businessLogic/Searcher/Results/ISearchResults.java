package com.sones.businessLogic.Searcher.Results;

import java.util.Iterator;

public interface ISearchResults 
{
	public	Iterator< IResult >	GetIterator();
	public	boolean	AddResult( final IResult result );
}
