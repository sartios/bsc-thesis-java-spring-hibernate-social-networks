package com.sones.dao.Facebook.Token;

import com.sones.buisinessLogic.Facebook.UserManager.FacebookToken;
import com.sones.buisinessLogic.Facebook.UserManager.IFacebookToken;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.AbstractDao;

public class FacebookTokenDao extends AbstractDao implements IFacebookTokenDao
{
	
	public	FacebookTokenDao()
	{
		super();
	}
	
	public	boolean	saverOrUpdate( final IFacebookToken token )
	{
		return	super.saveOrUpdate( token );
	}
	
	public	IFacebookToken	findByUserID( final String userID )
	{
		return	(IFacebookToken) super.find( FacebookToken.class, userID );
	}
	
	@Override
	public IFacebookToken GetToken(IApplicationUserID userID) 
	{
		return	findByUserID( userID.GetValue() );
	}
	
}
