package com.sones.dao.Facebook.Feed.Contents;

public interface IFacebookContentDaoFactory 
{
	public	IFacebookContentDao	GetContentDao( final String type );
}
