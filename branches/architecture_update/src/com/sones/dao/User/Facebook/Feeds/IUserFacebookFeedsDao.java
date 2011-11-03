package com.sones.dao.User.Facebook.Feeds;

import java.util.List;

public interface IUserFacebookFeedsDao 
{
	public	void	SaveUserFacebookFeeds( final String userID, final List<String> feedIDs );
	public	List< String >	GetUserFacebookFeeds( final String userID );
}
