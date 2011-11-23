package com.sones.businessLogic.LocationSpoter;


import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.ICapitalWordsSearchableFeeds;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.ICapitalWordsSearchableComments;
import com.sones.dao.CapitalWordsSpotter.Facebook.FacebookCommentsCapitalWordSpotterDao;
import com.sones.dao.CapitalWordsSpotter.Facebook.FacebookStatusCapitalWordSpotterDao;
import com.sones.dao.CapitalWordsSpotter.Facebook.IFacebookCommentCapitalWordSpotterDao;
import com.sones.dao.CapitalWordsSpotter.Facebook.IFacebookFeedCapitalWordSpotterDao;

public class FacebookFeedProvider	implements	IFeedProvider
{
	private	IFacebookFeedCapitalWordSpotterDao	_feedDao;
	private	IFacebookCommentCapitalWordSpotterDao	_commentDao;
	
	public	FacebookFeedProvider()
	{
		_feedDao	=	new	FacebookStatusCapitalWordSpotterDao();
		_commentDao	=	new	FacebookCommentsCapitalWordSpotterDao();
	}

	@Override
	public ICapitalWordsSearchableFeeds GetFeedsBetweenDates(String startDate, String endDate)
	{
		return	_feedDao.GetFeedListBetweenDates(startDate, endDate);
	}

	@Override
	public ICapitalWordsSearchableComments GetCommentsBetweenDates(String startDate, String endDate) 
	{
		return	_commentDao.GetCommentsBetweenDates(startDate, endDate);
	}

}
