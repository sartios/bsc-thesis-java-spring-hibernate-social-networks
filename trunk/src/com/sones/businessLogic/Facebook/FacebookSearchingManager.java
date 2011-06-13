package com.sones.businessLogic.Facebook;

import java.util.TimerTask;

import com.sones.businessLogic.AbstractSearcher;
import com.sones.businessLogic.FeedList;
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
	 * This sources from which we are going to download posts
	 */
	private FacebookSearchingList sources_ = null;
	
	/**
	 * The kind of searching we'll use, in case we have more than one style of search engines
	 */
	private AbstractSearcher searcher_ = null;
	
	/**
	 * The object we use for storing or retrieving feeds from database
	 */
	private FeedDao feedDao_ = null;
	
	/**
	 * This handler, implements the Facebook Graph API
	 */
	private FacebookRestHandler handler_ = null;
	
	/**
	 * User's toke for facebook
	 */
	private FacebookToken token_ = null;
	
	/**
	 * 
	 */
	private int searchTimeWindow_=0;
	
	/**
	 * The date from which and after we're going to download feeds
	 */
	private String sinceDateInUnixTimestamp=null;
	
	/**
	 * Date converter, from normal dates to unixstamps
	 */
	private DateConverter dateConverter=null;
	
	
	/**
	 * In the constructor, we initialize the {@link #token_} to be the fist of Table.
	 */
	public FacebookSearchingManager(){
		TokenDao dao = new TokenDao();
		token_ = (FacebookToken) dao.findAll().get(0);
		feedDao_ = new FeedDao();
		handler_ = new FacebookRestHandler();
		dao = null;
		setSinceDateInUnixTimestamp(new String());
	}

	
	/**
	 * In this constructor we take as argument since date in form MM/dd/yyyy HH:mm:ss . 
	 * We convert it into unix timestamp using com.sones.utilities.DateConverter class
	 * and set it into {@link #sinceDateInUnixTimestamp}.
	 * 
	 * If the argument isn't in form MM/dd/yyyy HH:mm:ss , it won't work in this version.
	 * 
	 * @param sinceDate in form MM/dd/yyyy HH:mm:ss
	 * 
	 * @version 1.0
	 */
	public FacebookSearchingManager(final String sinceDate){
		TokenDao dao = new TokenDao();
		token_ = (FacebookToken) dao.findAll().get(0);
		feedDao_ = new FeedDao();
		handler_ = new FacebookRestHandler();
		dao = null;
		dateConverter=new DateConverter();
	}
	
	public FacebookSearchingList getSources() {
		return sources_;
	}

	public void setSources(final FacebookSearchingList sources) {
		sources_ = sources;
	}

	public AbstractSearcher getSearcher() {
		return searcher_;
	}

	public void setSearcher(final AbstractSearcher searcher) {
		searcher_ = searcher;
	}

	public int getSearchTimeWindow() {
		return searchTimeWindow_;
	}

	public void setSearchTimeWindow(final int searchTimeWindow) {
		searchTimeWindow_ = searchTimeWindow;
	}
	
	private void buildIfNeed(){
		if(null==feedDao_){
			feedDao_ = new FeedDao();
		}
		if(null==handler_){
			handler_ = new FacebookRestHandler();
		}
	}
	
	
	/**
	 * This method is how we perform search.
	 * 1. If we have a list of sources, we go into a loop of getting each time one source's ID.
	 * 2. We download source's wall posts, the last 25.
	 * 3. We pass the feeds to searcher.
	 * 4. He performs searching algorithm.
	 * 
	 * @version 1.0
	 */
	public void performSearch(){
		System.out.println("Entered to searching perform");
		if(null!=sources_){
			String currentUserID;
			while(null!=(currentUserID=sources_.getID())){
				FeedList feeds = handler_.getFeeds(currentUserID, token_.getToken());
				searcher_.setFeeds(feeds);
				searcher_.search();
			}
		}
	}
	
	

	@Override
	/**
	 * We reset the sources pointer so it points to the beggining of the sources list
	 * and we perform search.
	 */
	public void run() {
		sources_.reset();
		performSearch();
		searchTimeWindow_++;
		System.out.println("Search "+searchTimeWindow_+" performed");
	}

	/**
	 * This method sets the {@link #sinceDateInUnixTimestamp} without checking the parameter.
	 * @param unixTimeStamp
	 */
	public void setSinceDateInUnixTimestamp(String unixTimeStamp) {
		if(null!=unixTimeStamp){
			this.sinceDateInUnixTimestamp = unixTimeStamp;
		}
	}

	public String getSinceDateInUnixTimestamp() {
		return sinceDateInUnixTimestamp;
	}
}
