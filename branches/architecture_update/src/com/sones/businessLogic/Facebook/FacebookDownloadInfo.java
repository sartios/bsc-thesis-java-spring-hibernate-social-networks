package com.sones.businessLogic.Facebook;

import java.util.ArrayList;
import java.util.List;

public class FacebookDownloadInfo	implements	IFacebookDownloadInfo
{
	private	List< String >	_feeds;
	private	List< String >	_comments;
	private	String	_date;
	private	String 	_userID;
	
	public	FacebookDownloadInfo()
	{
		_feeds	=	new	ArrayList<String>();
		_comments	=	new	ArrayList<String>();
	}
	
	@Override
	public void AddComments(List<String> commentIDs) 
	{
		_comments.addAll(commentIDs);
	}

	@Override
	public void AddFeedIDs(List<String> feedIDs) 
	{
		_feeds.addAll(feedIDs);
	}

	@Override
	public List<String> GetCommentIDs() 
	{
		return	_comments;
	}

	@Override
	public String GetDate() 
	{
		return	_date;
	}

	@Override
	public List<String> GetFeedIDs() 
	{
		return	_feeds;
	}

	@Override
	public void SetDate(String date) 
	{
		_date	=	date;
	}

	@Override
	public String GetUserID() 
	{
		return	_userID;
	}

	@Override
	public void SetUserID(String userID) 
	{
		_userID	=	userID;
	}

}
