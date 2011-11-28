package com.sones.controllers.results;

import com.sones.businessLogic.Facebook.FeedManager.IFacebookPicture;

public interface IPictureViewController
{
	public	IFacebookPicture	GetPicture(final String feedID);
}
