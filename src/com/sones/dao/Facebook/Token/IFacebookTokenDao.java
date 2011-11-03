package com.sones.dao.Facebook.Token;

import com.sones.buisinessLogic.Facebook.UserManager.IFacebookToken;
import com.sones.businessLogic.UserManager.IApplicationUserID;

public interface IFacebookTokenDao {

	public	IFacebookToken	GetToken( final IApplicationUserID userID );
}
