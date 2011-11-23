package com.sones.businessLogic.Facebook;

import com.sones.businessLogic.Facebook.DateManager.IFacebookDate;
import com.sones.businessLogic.UserManager.IApplicationUserID;

/**
 * 
 * @author Savramis Sartios
 *
 */
public interface IFacebookDateProvider 
{
	/**
	 * User sets a starting date which represents oldest feed publishing date.
	 * When downloading is going on, uses this date as a bound after which 
	 * downloads feeds.
	 * After first iteration, is the oldest database feed's date.
	 * 
	 * @param	{@link IFacebookUserID}
	 * @return	{@link IFacebookDate}
	 */
	public	IFacebookDate	GetDate( final	IApplicationUserID	applicationUserID );
}
