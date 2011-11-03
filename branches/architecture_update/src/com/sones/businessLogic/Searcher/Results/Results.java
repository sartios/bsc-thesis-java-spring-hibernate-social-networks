package com.sones.businessLogic.Searcher.Results;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

public class Results	implements	ISearchResults
{
	private	List< IResult >	_results;
	private	Logger	_logger;
	
	public	Results()
	{
		_results	=	new	ArrayList<IResult>();
		_logger	=	Logger.getLogger( Results.class );
	}

	@Override
	public boolean AddResult( final IResult result ) 
	{
		if( null != result)
		{
			_results.add( result );
			_logger.warn( " Addition succed! ");
			return	true;
		}
		return	false;
	}

	@Override
	public Iterator<IResult> GetIterator() 
	{
		return	_results.iterator();
	}

}
