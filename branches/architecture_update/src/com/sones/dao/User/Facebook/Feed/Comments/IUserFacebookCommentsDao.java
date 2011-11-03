package com.sones.dao.User.Facebook.Feed.Comments;

import java.util.List;

public interface IUserFacebookCommentsDao 
{
	public	boolean	SaveUserFacebookComments( final String userID, final List<String> commentIDs );
	public	boolean	SaveUserComment( final String userID, final String commentID);
}
