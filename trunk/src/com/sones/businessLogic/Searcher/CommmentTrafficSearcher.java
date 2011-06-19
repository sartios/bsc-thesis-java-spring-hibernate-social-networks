package com.sones.businessLogic.SearchOrganizer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.FeedList;
import com.sones.businessLogic.Facebook.FeedsWithCommentTraffic;
import com.sones.dao.FeedDao;
import com.sones.dao.FeedsWithCommentTrafficDao;

public class CommmentTrafficSearcher extends AbstractSearcher{

	private FeedDao dao_;
	private int distanceLimit_;
	private Set<String> hotFeeds_;
	private FeedsWithCommentTrafficDao trafficDao_;
	
	public CommmentTrafficSearcher(){
		dao_ = new FeedDao();
		distanceLimit_=2;
		trafficDao_=new FeedsWithCommentTrafficDao();
		hotFeeds_ = new HashSet<String>();
	}

	public int getDistanceLimit() {
		return distanceLimit_;
	}

	public void setDistanceLimit(final int distanceLimit) {
		distanceLimit_ = distanceLimit;
	}

	@Override
	public void search() {
		this.clearHotFeeds();
		FeedList feeds_ =  super.getFeeds();
		if(null!=feeds_){
			FeedsWithCommentTraffic listOfFeedsWithTraffic = new FeedsWithCommentTraffic(true);
			Map<String,String> feedsWithTraffic = new HashMap<String, String>();
			Feed feed;
			String currentFeedID;
			int feedsSize = feeds_.getSize();
		
			for(int i=0;i<feedsSize;i++){
				feed = feeds_.getFeed(i);
				currentFeedID = feed.getId_();
				Feed oldFeed = dao_.find(currentFeedID);
				if(null==oldFeed){
					dao_.saveOrUpdate(feed);
				}
				else{
					int nf_numOfCom = feed.getNumberOfComments_();
					int of_numOfCom = oldFeed.getNumberOfComments_();
					int distance = nf_numOfCom - of_numOfCom;
					if(distanceLimit_<=distance){
						feedsWithTraffic.put(feed.getId_(),String.valueOf(distance));
						hotFeeds_.add(feed.getId_());
					}
					dao_.saveOrUpdate(feed);
				}
			}
			listOfFeedsWithTraffic.setFeeds(feedsWithTraffic);
			trafficDao_.saveOrUpdate(listOfFeedsWithTraffic);
		}
	}
	
	private void clearHotFeeds(){
		hotFeeds_.clear();
	}
	
	public Set<String> getCurrentFeedsWithCommentTraffic(){
		return hotFeeds_;
	}
}
