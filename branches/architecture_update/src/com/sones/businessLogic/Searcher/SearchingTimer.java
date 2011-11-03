package com.sones.businessLogic.Searcher;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.sones.businessLogic.Searcher.Facebook.FacebookSearcher;

public class SearchingTimer	implements	ISearchingTimer
{
	private	Timer	_timer;
	private	ISearcher	_searcher;
	private	long	_time;
	
	private	Logger	_logger;
	
	public	SearchingTimer()
	{
		_timer	=	new	Timer();
		_logger	=	Logger.getLogger( SearchingTimer.class );
	}
	
	@Override
	public void Start() 
	{
		_timer.schedule( ((TimerTask) _searcher), _time,_time );
		_logger.debug( " Starting the thread" );
	}
	
	@Override
	public void Stop() 
	{
		_timer.cancel();
	}

	@Override
	public void SetSearcher( final ISearcher searcher) 
	{
		if( IsNotNull( searcher ) )
		{
			_searcher	=	searcher;
		}
	}

	@Override
	public void SetTime(final String time) 
	{
		_time	=	new	Long( time );
	}
	
	private	boolean	IsNotNull( final Object object )
	{
		if( null == object)
		{
			_logger.error( " Object is NULL " );
			return	false;
		}
		return	true;
	}

}
