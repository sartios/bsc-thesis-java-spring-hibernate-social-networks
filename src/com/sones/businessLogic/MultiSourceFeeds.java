package com.sones.businessLogic;

import java.util.HashSet;
import java.util.Set;

/**
 * This class contains ids of feeds from different sources and sites.
 * @author Sartios
 *
 */
public class MultiSourceFeeds {

	private Set<String> facebookFeeds_;
	
	public MultiSourceFeeds(){
		facebookFeeds_ = new HashSet<String>();
	}
	
	public void setFacebookFeeds(final Set<Feed> feeds){
		int size = feeds.toArray().length;
		for(int i=0;i<size;i++){
			this.facebookFeeds_.add(((Feed) (feeds.toArray())[i]).getId_());
		}
	}

	
	public Set getFacebookFeeds(){
		if(null!=facebookFeeds_){
			return this.facebookFeeds_;
		}
		return null;
	}
}
