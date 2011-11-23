package com.sones.businessLogic.Facebook;

import com.sones.businessLogic.Facebook.DateManager.FacebookValidDate;
import com.sones.businessLogic.Facebook.DateManager.IFacebookDate;
import com.sones.businessLogic.UserManager.IApplicationUserID;

public class FacebookZeroDateProvider implements	IFacebookDateProvider
{

	@Override
	public IFacebookDate GetDate(IApplicationUserID applicationUserID)
	{
		return	new	FacebookValidDate();
	}
	
}
