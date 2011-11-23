package com.sones.businessLogic.Facebook;

import com.sones.buisinessLogic.Facebook.UserManager.IFacebookUserID;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.IFacebookComments;
import com.sones.businessLogic.UserManager.IApplicationUserID;

public interface IFacebookCommentSaver 
{
	public	boolean	SaveUserFacebookComments( final IApplicationUserID userID, final IFacebookComments comments );
	public	boolean	AssociateUserWithComments( final IApplicationUserID userID, final IFacebookComments comments );
	public	boolean	SaveComments( final IFacebookComments comment );
}
