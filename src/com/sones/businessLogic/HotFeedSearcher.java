package com.sones.businessLogic;

import java.util.Set;

import com.sones.dao.FeedDao;

public class HotFeedSearcher extends AbstractSearcher{

	private FeedDao dao_;
	private int distanceLimit_;
	private Set<String> hotFeeds_;
	
	public HotFeedSearcher(){
		dao_ = new FeedDao();
		distanceLimit_=2;
	}

	public int getDistanceLimit() {
		return distanceLimit_;
	}

	public void setDistanceLimit(final int distanceLimit) {
		distanceLimit_ = distanceLimit;
	}

	@Override
	public void search() {
		FeedList feeds_ =  super.getFeeds();
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
				System.out.println(distance);
				if(distanceLimit_<=distance){
					hotFeeds_.add(feed.getId_());
				}
				dao_.saveOrUpdate(feed);
			}
		}
	}
	
	public void clearHotFeeds(){
		hotFeeds_.clear();
	}
}
