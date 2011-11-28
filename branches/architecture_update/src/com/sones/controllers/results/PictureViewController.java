package com.sones.controllers.results;

import com.sones.businessLogic.Facebook.FeedManager.IFacebookPicture;
import com.sones.businessLogic.Facebook.FeedManager.PictureContent;
import com.sones.dao.Facebook.Feed.Contents.IFacebookContentDao;
import com.sones.dao.Facebook.Feed.Contents.PictureContentDao;

public class PictureViewController	implements	IPictureViewController
{

	private	IFacebookContentDao	_dao;
	
	public	PictureViewController()
	{
		_dao	=	new	PictureContentDao();
	}
	
	@Override
	public IFacebookPicture GetPicture(String feedID)
	{
		IFacebookPicture	picture	=	new PictureContent();
		if( null != feedID )
		{
			picture	=	(IFacebookPicture) _dao.GetContent(feedID);
		}
		return	picture;
	}

}
