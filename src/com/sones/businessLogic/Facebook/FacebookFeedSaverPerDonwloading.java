package com.sones.businessLogic.Facebook;

import java.util.ArrayList;
import java.util.List;

import com.sones.businessLogic.Downloader.IDownloadingDateGenerator;
import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeeds;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.IFacebookComments;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.Facebook.DownloadInfo.IFacebookDownloadInfoDao;

public class FacebookFeedSaverPerDonwloading	implements	IFacebookFeedSaver
{

	private	IFacebookFeedSaver	_feedSaver;
	private	IDownloadingDateGenerator	_dateGenerator;
	private	IFacebookDownloadInfoDao	_downloadInfoDao;
	
	public	FacebookFeedSaverPerDonwloading()
	{
	}
	
	@Override
	public boolean SaveUserFacebookFeeds(IApplicationUserID userID,	IFacebookFeeds feeds) 
	{
		List< String >	feedIDs	=	feeds.GetFeedIDs();
		List< String > 	commentIDs	=	GetCommentIDs( feeds )	;
		IFacebookDownloadInfo	downloadInfo	=	new	FacebookDownloadInfo();
		
		downloadInfo.AddComments(commentIDs);
		downloadInfo.AddFeedIDs(feedIDs);
		downloadInfo.SetDate( _dateGenerator.GetDownloadingDate() );
		downloadInfo.SetUserID( userID.GetValue() );
		
		_feedSaver.SaveUserFacebookFeeds(userID, feeds);
		_downloadInfoDao.SaveDownloadInfo(downloadInfo);
		
		return	false;
	}
	
	public	void	SetFacebookFeedSaver( final IFacebookFeedSaver feedSaver )
	{
		_feedSaver	=	feedSaver;
	}
	
	public void SetDateGenerator(IDownloadingDateGenerator dateGenerator)
	{
		_dateGenerator = dateGenerator;
	}

	public IDownloadingDateGenerator GetDateGenerator() 
	{
		return _dateGenerator;
	}	
	
	public	void	SetDownloadInfoDao( IFacebookDownloadInfoDao	downloadInfoDao )
	{
		_downloadInfoDao	=	downloadInfoDao;
	}
	
	private	List< String >	GetCommentIDs( IFacebookFeeds feeds )
	{
		List< String >	commentIDs	=	new	ArrayList< String >();
		
		for( int feedIndex = 0; feedIndex < feeds.GetSize(); feedIndex++)
		{
			AddCommentsIntoCommentIDCollection( commentIDs, feeds.GetFeed(feedIndex).GetComments() );
		}
		
		return	commentIDs;
	}

	private void AddCommentsIntoCommentIDCollection(List<String> commentIDs, IFacebookComments comments) 
	{
		List< String > ids = comments.GetCommentIDs();
		commentIDs.addAll( ids );
	}
}
