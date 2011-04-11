package com.sones.businessLogic.Facebook;

import java.util.TimerTask;

import com.sones.businessLogic.AbstractSearcher;
import com.sones.businessLogic.FeedList;
import com.sones.dao.FeedDao;
import com.sones.dao.TokenDao;

public class FacebookSearchingManager extends TimerTask{

	private FacebookSearchingList sources_ = null;
	private AbstractSearcher searcher_ = null;
	private FeedDao feedDao_ = null;
	private FacebookRestHandler handler_ = null;
	private FacebookToken token_ = null;
	private int searchTimeWindow_=0;
	
	public FacebookSearchingManager(){
		TokenDao dao = new TokenDao();
		token_ = (FacebookToken) dao.findAll().get(0);
		feedDao_ = new FeedDao();
		handler_ = new FacebookRestHandler();
		dao = null;
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
	
	public void performSearch(){
		System.out.println("Entered to searching perform");
		if(null!=sources_){
			String currentUserID;
			while(null!=(currentUserID=sources_.getID())){
				FeedList feeds = handler_.getFeeds(currentUserID, token_.getToken());
				feedDao_.saveUserFeeds(feeds);
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
	
}
