package com.sones.businessLogic.Facebook;

import java.util.TimerTask;

import com.sones.businessLogic.SearchOrganizer.AbstractSearcher;
import com.sones.businessLogic.SearchOrganizer.KeywordSearcher;
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
	private long sinceDateInUnixTimestamp=0;
	
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
		setSinceDateInUnixTimestamp(new String("0"));
	}

	
	/**
	 * In this constructor we take as argument since date in form MM/dd/yyyy HH:mm:ss . 
	 * We convert it into unix timestamp using com.sones.utilities.DateConverter class
	 * and set it into {@link #sinceDateInUnixTimestamp}.
	 * 
	 * If the argument isn't in form MM/dd/yyyy HH:mm:ss , it won't work in this version.
	 * 
	 * In the constructor, we set straight the {@link #sinceDateInUnixTimestamp} because we 
	 * want exactly the given date.
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
		sinceDateInUnixTimestamp=dateConverter.getUnixTimestamp(sinceDate);
	}
	
	public FacebookSearchingList getSources() {
		return sources_;
	}

	public void setSources(final FacebookSearchingList sources) {
		if(null!=sources){
			sources_ = sources;
		}
		else{
			sources_=new FacebookSearchingList();
		}
	}

	public AbstractSearcher getSearcher() {
		return searcher_;
	}

	public void setSearcher(final AbstractSearcher searcher) {
		if(null!=searcher){
			searcher_ = searcher;
		}
		else{
			searcher_ = new KeywordSearcher();
		}
	}

	public int getSearchTimeWindow() {
		return searchTimeWindow_;
	}

	public void setSearchTimeWindow(final int searchTimeWindow) {
		searchTimeWindow_ = searchTimeWindow;
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
		searcher_.clearFeeds();
		sources_.reset();
		if(null!=sources_){
			String currentUserID;
			while(null!=(currentUserID=sources_.getID())){
				long date = getDate(currentUserID);
				FeedList feeds = getFeeds(currentUserID, token_.getToken(),date);
				if(!feeds.isEmpty()){
					searcher_.setFeeds(feeds);
					String newerFeedDate=(feeds.getNewerFeed().getCreatedTime_());
					this.setSinceDateInUnixTimestamp(newerFeedDate);
					searcher_.search();
				}
			}
		}
	}
	
	/**
	 * We need the most recent date for searching. So the user gives a starting date and then
	 * we take the date of the most recent feed. There are some issues:
	 * 1. the feeds we have saved in the database has more recent date than what the user give
	 * 2. there are users that haven't feeds in the database so we don't the date to search
	 * 
	 * @param userID
	 * @return date to search
	 */
	public long getDate(final String userID){
		long date=0;
		if(null!=userID){
			FeedDao dao = new FeedDao();
			long dateFromDB = dateConverter.getUnixTimestamp(dao.findMostRecentUserFeedDate(userID));
			if(dateFromDB<this.sinceDateInUnixTimestamp){
				date=this.sinceDateInUnixTimestamp;
			}
			else{
				date=dateFromDB;
			}
		}
		return date;
	}
	
	private FeedList getFeeds(final String userID,final String token,final long date){
		return handler_.getFeedsSinceDate(userID,token,date);
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
	 * This method sets the {@link #sinceDateInUnixTimestamp}.
	 * We don't have to increase the timestamp, because if you give 
	 * the creation time of a feed, won't return you this feed. 
	 * It returns -2 sec or +2 sec feeds. 
	 * If there not new feeds and we search by giving the time of last feed creation
	 * we will get an empty array.
	 * @param unixTimeStamp
	 */
	public void setSinceDateInUnixTimestamp(String facebookDate) {
		if(null!=facebookDate){
			this.sinceDateInUnixTimestamp = dateConverter.getUnixTimestamp(facebookDate);
		}
	}

	public long getSinceDateInUnixTimestamp() {
		return sinceDateInUnixTimestamp;
	}
	
}
