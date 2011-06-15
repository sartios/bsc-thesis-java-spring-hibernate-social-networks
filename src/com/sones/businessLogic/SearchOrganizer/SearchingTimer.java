package com.sones.businessLogic.SearchOrganizer;

import java.util.Timer;
import java.util.TimerTask;

import com.sones.businessLogic.Facebook.FacebookRestHandler;
import com.sones.businessLogic.Facebook.FacebookSearchingList;
import com.sones.businessLogic.Facebook.FacebookSearchingManager;
import com.sones.businessLogic.Facebook.FacebookToken;
import com.sones.dao.FeedDao;
import com.sones.dao.TokenDao;

public class SearchingTimer {

	//private static FacebookSearchingList sources_ = null;
	private Timer facebookTimer_;
	//private static FacebookRestHandler handler_ = null;
	//private static FacebookToken token_ = null;
	private int time_;
	//private static FeedDao feedDao_;
	private FacebookSearchingManager facebookSearchingManager;
	
	public SearchingTimer(){
		facebookTimer_= new Timer();
		
		//feedDao_ = new FeedDao();
		//handler_ = new FacebookRestHandler();
		//TokenDao dao = new TokenDao();
		//token_ = (FacebookToken) dao.findAll().get(0);
		//dao=null;
		time_=-1;
	}
	
	public SearchingTimer(final int time){
		facebookTimer_= new Timer();
		if(0<time){
			time_ = time;
		}
	}
	
	public void setFacebookSearchingManager(final FacebookSearchingManager manager){
		facebookSearchingManager = manager;
	}

	/*public FacebookSearchingList getSources() {
		return sources_;
	}

	public void setSources(final FacebookSearchingList sources) {
		sources_ = sources;
	}
*/
	public int getTime() {
		return time_;
	}

	public void setTime(final int time) {
		if(0<time){
			time_ = time;
		}
	}
	
	public void start(){
		facebookTimer_.schedule(facebookSearchingManager, time_,time_);
	}
	
	/*public static MultiSourceFeeds getFeeds(){
		MultiSourceFeeds feeds = new MultiSourceFeeds();
		if(null!= sources_){
			String currentUserID;
			while(null!=(currentUserID=sources_.getID())){
				FeedList tmp = handler_.getFeeds(currentUserID, token_.getToken());
				feeds.setFacebookFeeds(tmp.getFeeds_());
				feedDao_.saveUserFeeds(tmp);
			}
		}
		return feeds;
	}*/
}
