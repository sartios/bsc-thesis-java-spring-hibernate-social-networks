package com.sones.businessLogic.Facebook;

import java.util.TimerTask;

import com.sones.businessLogic.SocialNetworkUser;
import com.sones.businessLogic.Facebook.Rest.FacebookRestHandler;
import com.sones.businessLogic.Facebook.Source.FacebookFriend;
import com.sones.businessLogic.Facebook.Source.FacebookUser;
import com.sones.businessLogic.Facebook.Source.ISource;
import com.sones.businessLogic.Searcher.AbstractSearcher;
import com.sones.businessLogic.Searcher.ISearcher;
import com.sones.businessLogic.Searcher.KeywordSearcher;
import com.sones.businessLogic.Searcher.Searcher;

import com.sones.dao.FeedDao;
import com.sones.dao.TokenDao;
import com.sones.utilities.DateConverter;


/**
 * This class is responsible for downloading feeds and searching for keywords into them,
 * in a period of time. It's extending the TimerTask class.
 * 
 * 
 * @author Sartios
 *
 */
public class FacebookSearchingManager extends TimerTask{

	/**
	 * Facebook user for whom we are going to permorf search
	 */
	private SocialNetworkUser facebookUser_=null;
	
	/**
	 * The kind of searching we'll use, in case we have more than one style of search engines
	 */
	private ISearcher searcher = null;
	
	/**
	 * 
	 */
	private int searchTimeWindow_=0;	
	
	
	/**
	 * No arguments constructor
	 */
	public FacebookSearchingManager(final SocialNetworkUser user){
		searcher = new KeywordSearcher(user);
		this.facebookUser_=user;
	}

	public int getSearchTimeWindow() {
		return searchTimeWindow_;
	}

	public void setSearchTimeWindow(final int searchTimeWindow) {
		searchTimeWindow_ = searchTimeWindow;
	}
	
	/**
	 * We reset the sources pointer so it points to the beggining of the sources list
	 * and we perform search.
	 */
	@Override
	public void run() {
		searcher.search();
		searchTimeWindow_++;
		System.out.println("Search "+searchTimeWindow_+" performed");
	}
	
	public ISearcher getSearcher(){
		return searcher;
	}
	
}
