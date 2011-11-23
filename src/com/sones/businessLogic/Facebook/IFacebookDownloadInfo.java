package com.sones.businessLogic.Facebook;

import java.util.List;

/**
 * Provides methods and properties for retrieving feeds and comments
 * that were downloaded at the specific download.
 * 
 * @author Savramis Sartios
 *
 */
public interface IFacebookDownloadInfo 
{
	/**
	 * Returns a collection of feeds that were downloaded at that download.
	 * @return	IDs of Facebook feeds.
	 */
	public	List< String >	GetFeedIDs();
	
	/**
	 * Adds a collection of feed ID at the collection of feeds that were downloaded.
	 * @param feedIDs the IDs of the feeds.
	 */
	public	void	AddFeedIDs( List< String > feedIDs );
	
	/**
	 * Returns a collection of IDs for the comments that were downloaded at that download.
	 * @return	IDs of Facebook comments.
	 */
	public	List< String >	GetCommentIDs();
	
	/**
	 * Adds a collection of comment IDs at the collection of comments that were downloaded at that download.
	 * @param commentIDs a collection of comments IDs.
	 */
	public	void	AddComments( List< String > commentIDs );
	
	/**
	 * Returns the date of that the downloading happened.
	 * @return Date of downloading.
	 */
	public	String	GetDate();
	
	/**
	 * Sets the date of the downloading.
	 * @param date to be setted.
	 */
	public	void	SetDate( String date );
	
	/**
	 * Sets application user id
	 * 
	 * @param userID to be setted.
	 */
	public	void	SetUserID( String userID );
	
	/**
	 * Returns application user ID.
	 * @return application user ID.
	 */
	public	String	GetUserID();
}
