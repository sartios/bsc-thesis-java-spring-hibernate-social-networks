package com.sones.businessLogic.Searcher.Facebook;

import java.util.Iterator;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.sones.businessLogic.KeywordManager.IKeyword;
import com.sones.businessLogic.KeywordManager.IKeywords;
import com.sones.businessLogic.KeywordManager.KeywordRetriever;
import com.sones.businessLogic.Searcher.IKeywordRetriever;
import com.sones.businessLogic.Searcher.ISearchResultsSaver;
import com.sones.businessLogic.Searcher.ISearchableFeed;
import com.sones.businessLogic.Searcher.ISearcher;
import com.sones.businessLogic.Searcher.Results.IResult;
import com.sones.businessLogic.Searcher.Results.ISearchResults;
import com.sones.businessLogic.Searcher.Results.PersistableResult;
import com.sones.businessLogic.Searcher.Results.Results;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.Searcher.Results.ResultsDao;

public class FacebookSearcher	extends	TimerTask	implements	ISearcher
{
	private	IFacebookDownloadedFeedRetriever	_feedRetriever;
	private	IKeywordRetriever	_keywordRetriever;
	private	ISearchResultsSaver	_resultSaver;
	private	Logger	_logger;
	private	 IApplicationUserID _userID;
	
	public	FacebookSearcher()
	{
		_keywordRetriever	=	new	KeywordRetriever();
		_feedRetriever	=	new	FacebookDownloadedFeedRetriever();
		_resultSaver	=	new	ResultsDao();
		_logger	=	Logger.getLogger( FacebookSearcher.class );
	}

	@Override
	public boolean Search()
	{
		IKeywords keywords	=	_keywordRetriever.GetKeywords( _userID );
		Iterator< ISearchableFeed >	feedIterator	=	_feedRetriever.GetFeeds( _userID ).GetIterator();
		ISearchResults	results	=	new	Results();
		
		boolean	isCompleted	=	false;
		if( IsNotEmpty( feedIterator ) )
		{
			_logger.warn(" Keywords and feeds exist ");
			for( ; feedIterator.hasNext(); )
			{
				ISearchableFeed	feed	=	feedIterator.next();
				_logger.warn( " For feed: "+feed.GetID() );
				
				Iterator< IKeyword > keywordIterator	=	keywords.GetIterator();
				for( ; keywordIterator.hasNext(); )
				{
					IKeyword	keyword	=	keywordIterator.next();
					_logger.warn( " For keyword: "+keyword.GetValue() );
					if( feed.isContaining( keyword.GetValue() ) )
					{
						_logger.warn( " It contains keyword ");
						IResult	result	=	new	PersistableResult();
						result.SetKeyword( keyword.GetID() );
						result.SetFeedID( feed.GetID() );
						results.AddResult( result );
					}
				}
				_logger.warn( " Next feed " );
			}
			_resultSaver.SaverResults( _userID, results );
			isCompleted	=	true;
		}
		return	isCompleted;
	}

	@Override
	public void run() 
	{
		_logger.warn( " Searching starts" );
		Search();
	}

	@Override
	public void SetApplicationUser(IApplicationUserID userID) 
	{
		if( null != userID )
		{
			_userID	=	userID;
		}
	}
	
	private	boolean	IsNotEmpty( final Iterator iterator )
	{
		if( iterator.hasNext() )
		{
			return	true;
		}
		_logger.warn( " Iterator is empty " );
		return	false;
	}

}
