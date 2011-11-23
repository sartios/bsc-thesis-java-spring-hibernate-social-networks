package com.sones.businessLogic.Facebook.FeedSources;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sones.buisinessLogic.Facebook.UserManager.IFacebookUserID;

public class FacebookSources implements	IFacebookSources
{
	private	List< IFacebookUserID >	_sourcesID;
	
	public	FacebookSources()
	{
		_sourcesID	=	new	ArrayList< IFacebookUserID >();
	}

	public	boolean	AddSource( final IFacebookUserID sourceID )
	{
		return	_sourcesID.add( sourceID );
	}
	
	@Override
	public Iterator<IFacebookUserID> GetIterator() 
	{
		return	_sourcesID.iterator();
	}

	@Override
	public int GetSize() 
	{
		return	_sourcesID.size();
	}

	@Override
	public boolean IsEmpty()
	{
		return	_sourcesID.isEmpty();
	}

}
