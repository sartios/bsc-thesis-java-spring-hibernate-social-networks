package com.sones.dao.CapitalWordsSpotter.Facebook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.persistance.HibernateUtil;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.FacebookCapitalWordFeeds;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.FacebookCapitalWordSearchableFeed;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.ICapitalWordsSearchableFeed;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.ICapitalWordsSearchableFeeds;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents.CapitalizedFacebookStatusMessage;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents.ICapitalWordsSearchableContent;
import com.sones.dao.AbstractDao;

public class FacebookStatusCapitalWordSpotterDao	extends	AbstractDao	implements	IFacebookFeedCapitalWordSpotterDao
{
	private	PropertiesConfiguration	_config;
	
	public	FacebookStatusCapitalWordSpotterDao()
	{
		super();
		try 
		{
			_config	=	new	PropertiesConfiguration("config/SqlScripts/FeedsWithCapitalizedWords/Facebook/status_message.properties");
		} 
		catch (ConfigurationException e) 
		{
			e.printStackTrace();
		}
	}
	
	public	List<Object>	GetFacebookFeed( String feedID, String type )
	{
		List<Object>	feeds	=	null;
		try
		{
			Query	query	=	session.createSQLQuery( _config.getString( "sql.query" ) );
			feeds	=	query.list();
			tx.commit();
			session.flush();
		}
		catch (HibernateException ex)
		{
			
		}
		finally
		{
			HibernateUtil.close(session);
		}
		return	feeds;
	}
	
	@Override
	public	List<ICapitalWordsSearchableFeed>	GetFeedsBetweenDates(String startDate, String endDate)
	{
		List<ICapitalWordsSearchableFeed>	feeds	=	null;
		startOperation();
		try
		{			System.out.println(_config.getString( "get.feeds.between.dates" ));
			Query	query	=	session.createSQLQuery( _config.getString( "get.feeds.between.dates" ) );
			query.setParameter(_config.getString( "start.date" ), startDate);
			query.setParameter(_config.getString( "end.date" ), endDate);

			List objects	=	query.list();

			tx.commit();
			session.flush();
			feeds	=	GetStatusFeeds(objects);
			
		}
		catch (HibernateException ex)
		{
		}
		finally
		{
			HibernateUtil.close(session);
		}
		return	feeds;
	}
	
	private	List<ICapitalWordsSearchableFeed>	GetStatusFeeds( List<Object[]> objects )
	{
		List<ICapitalWordsSearchableFeed>	feeds	=	new	ArrayList<ICapitalWordsSearchableFeed>();
		Iterator<Object[]>	iterator	=	objects.iterator();
		while(iterator.hasNext())
		{
			System.out.println("Has next");
			ICapitalWordsSearchableFeed	feed	=	new	FacebookCapitalWordSearchableFeed();
			Object[]	object	=	iterator.next();
			feed.SetFeedID((String)object[1]);
			System.out.println("Feed ID: " +(String)object[1]);
			ICapitalWordsSearchableContent	content	=	new	CapitalizedFacebookStatusMessage();
			((CapitalizedFacebookStatusMessage)content).SetMessage((String)object[0]);
			System.out.println("Feed message: " +(String)object[0]);
			((FacebookCapitalWordSearchableFeed)feed).SetContent(content);
			feeds.add(feed);
		}
		System.out.println("Has not next");
		return	feeds;
	}

	@Override
	public ICapitalWordsSearchableFeeds GetFeedListBetweenDates( String startDate, String endDate) 
	{
		ICapitalWordsSearchableFeeds	feeds	=	new	FacebookCapitalWordFeeds();
		((FacebookCapitalWordFeeds)feeds).AddFeeds(GetFeedsBetweenDates(startDate, endDate));
		return	feeds;
	}
}
