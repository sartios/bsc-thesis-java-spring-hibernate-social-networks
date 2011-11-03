package com.sones.businessLogic.Facebook.FeedSources;

import java.util.Iterator;

import com.sones.buisinessLogic.Facebook.UserManager.IFacebookUserID;

/**
 * Collection of Facebook Sources
 * 
 * @author Savramis Sartios
 *
 */
public interface IFacebookSources 
{
	/**
	 * Adds a source into the collection
	 * @param sourceID {@link IFacebookUserID}
	 * @return true if addition succeed
	 */
	public	boolean	AddSource( final IFacebookUserID sourceID );
	
	/**
	 * @return size of the collection
	 */
	public	int	GetSize();
	
	/**
	 * @return true if collection is empty
	 */
	public	boolean	IsEmpty();
	
	/**
	 * @return	{@link Iterator} for the collection
	 */
	public	Iterator< IFacebookUserID >	GetIterator();
}
