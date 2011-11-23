package com.sones.businessLogic.Facebook.FeedManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/**
 * This class is keeping a collection of feeds from a specific user.
 * @author Sartios
 *
 */
public class FacebookFeedList implements IFacebookFeeds{

	
	/**
	 * The collection of the user's feeds
	 */
	private List<IFacebookFeed> feeds_ = new ArrayList<IFacebookFeed>();
	
	/**
	 * No arguments constructor
	 */
	public FacebookFeedList(){
		feeds_ =  new ArrayList<IFacebookFeed>();
	}
	

	/**
	 * Returns the collection of feeds
	 * @return set of feeds
	 */
	public List GetFeeds() {
		return feeds_;
	}
	
	/**
	 * Returns the size of the internal collection
	 * @return size of collection
	 */
	public int GetSize(){
		return feeds_.size();
	}
	
	
	/**
	 * Returns true if there aren't feeds into the set
	 * @return true if the set is empty
	 */
	public boolean IsEmpty(){
		return this.feeds_.isEmpty();
	}
	
	/**
	 * Clears the set of feeds that we keep
	 */
	public void ClearFeeds(){
		this.feeds_.clear();
	}


	@Override
	public void Add(IFacebookFeed feed) {
		if(null!=feed){
			this.feeds_.add(feed);
		}
	}


	@Override
	public boolean isEmpty() {
		return	IsEmpty();
	}

	@Override
	public IFacebookFeed GetFeed(int index) {
		return	feeds_.get( index );
	}


	@Override
	public List<String> GetFeedIDs() {
		List< String > IDs	=	new	ArrayList< String >();
		for( int index = 0; index < feeds_.size(); index++ )
		{
			IDs.add( feeds_.get( index ).GetID() );
		}
		return	IDs;
	}	
}