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
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents.CapitalizedFacebookLink;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents.ICapitalWordsSearchableContent;
import com.sones.dao.AbstractDao;

public class FacebookLinkCapitalWordSpotterDao 	extends	AbstractDao	implements	IFacebookFeedCapitalWordSpotterDao
{
	private	PropertiesConfiguration	_config;
	
	public	FacebookLinkCapitalWordSpotterDao()
	{
		super();
		try 
		{
			_config	=	new	PropertiesConfiguration("config/SqlScripts/FeedsWithCapitalizedWords/Facebook/link.properties");
		} 
		catch (ConfigurationException e) 
		{
			e.printStackTrace();
		}
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
			feeds	=	GetLinks(objects);
			
		}
		catch (HibernateException ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			HibernateUtil.close(session);
		}
		return	feeds;
	}
	
	private	List<ICapitalWordsSearchableFeed>	GetLinks( List<Object[]> objects )
	{
		List<ICapitalWordsSearchableFeed>	feeds	=	new	ArrayList<ICapitalWordsSearchableFeed>();
		Iterator<Object[]>	iterator	=	objects.iterator();
		while(iterator.hasNext())
		{
			ICapitalWordsSearchableFeed	feed	=	new	FacebookCapitalWordSearchableFeed();
			Object[]	object	=	iterator.next();
			feed.SetFeedID((String)object[_config.getInt( "feed.index" )]);
			ICapitalWordsSearchableContent	content	=	new	CapitalizedFacebookLink();
			((CapitalizedFacebookLink)content).SetMessage((String)object[_config.getInt( "message.index" )]);
			((CapitalizedFacebookLink)content).SetCaption((String)object[_config.getInt( "caption.index" )]);
			((CapitalizedFacebookLink)content).SetDescription((String)object[_config.getInt( "description.index" )]);
			((CapitalizedFacebookLink)content).SetName((String)object[_config.getInt( "name.index" )]);
			((FacebookCapitalWordSearchableFeed)feed).SetContent(content);
			feeds.add(feed);
		}
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
