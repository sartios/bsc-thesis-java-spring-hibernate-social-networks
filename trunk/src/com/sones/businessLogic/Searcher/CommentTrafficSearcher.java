package com.sones.businessLogic.Searcher;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sones.businessLogic.Facebook.Comment;
import com.sones.businessLogic.Facebook.CommentList;
import com.sones.businessLogic.Facebook.FacebookRestHandler;
import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.FeedList;
import com.sones.businessLogic.Facebook.FeedsWithCommentTraffic;
import com.sones.businessLogic.KeywordManager.Keyword;
import com.sones.businessLogic.KeywordManager.KeywordList;
import com.sones.dao.FeedDao;
import com.sones.dao.FeedsWithCommentTrafficDao;
import com.sones.dao.KeywordDao;

public class CommentTrafficSearcher extends AbstractSearcher{

	private FeedDao dao_;
	private int distanceLimit_;
	private Set<String> hotFeeds_;
	private FeedsWithCommentTrafficDao trafficDao_;
	private Map<String,String> feedsWithTraffic;

	
	public CommentTrafficSearcher(){
		dao_ = new FeedDao();
		distanceLimit_=2;
		trafficDao_=new FeedsWithCommentTrafficDao();
		hotFeeds_ = new HashSet<String>();
		feedsWithTraffic = new HashMap<String, String>();
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
		this.findFeedsWithCommentTraffic();
		if(this.thereAreFeedsWithCommentTraffic()){
			FeedDao feedDao = new FeedDao();
			KeywordDao keywordDao = new KeywordDao();
			KeywordList keywords = keywordDao.getKeywordList();
			Set feeds = getCurrentFeedsWithCommentTraffic();
			for(int keywordIndex=0;keywordIndex<keywords.getSize();keywordIndex++){
				for(int feedIndex=0;feedIndex<feeds.size();feedIndex++){
					String feedID=(String) feeds.toArray()[feedIndex];
					CommentList comments = this.getNewComments(feedID);
					String keywordValue = keywords.getKeyword(keywordIndex).getValue();
					if(comments.contain(keywordValue)){
						Feed feed=feedDao.find(feedID);
						if(null==feed){
							FacebookRestHandler handler = new FacebookRestHandler();
							feed=handler.getFeed(feedID);
						}
						keywords.getKeyword(keywordIndex).addID(feed);
					}
				}
			}
			keywordDao.saveKeywordList(keywords);
		}
		else{
			System.out.println("There aren't new comments");
		}
	}
	
	public void findFeedsWithCommentTraffic(){
		
		FeedList feeds=super.getFeeds();
		//System.out.println(feeds.getFeeds_().size());

		if(null!=feeds){
			FeedsWithCommentTraffic listOfFeedsWithTraffic = new FeedsWithCommentTraffic(true);
			Feed feed;
			String currentFeedID;
			int feedsSize = feeds.getSize();
		
			for(int i=0;i<feedsSize;i++){
			//	System.out.println(i);
				feed = feeds.getFeed(i);
				currentFeedID = feed.getId_();
				Feed oldFeed = dao_.find(currentFeedID);
				if(null==oldFeed){
				//	System.out.println("Feed is null");
					dao_.saveOrUpdate(feed);
				}
				else{
					int nf_numOfCom = feed.getNumberOfComments_();
					
					int of_numOfCom = oldFeed.getNumberOfComments_();
					int distance = nf_numOfCom - of_numOfCom;
				//	System.out.println("ID: "+feed.getId_()+" "+nf_numOfCom+"-"+of_numOfCom+"="+distance);
					if(0<distance){
					//	System.out.println(distance);
						feedsWithTraffic.put(feed.getId_(),String.valueOf(distance));
						hotFeeds_.add(feed.getId_());
					}
					dao_.saveOrUpdate(feed);
				}
			}
			listOfFeedsWithTraffic.setFeeds(feedsWithTraffic);
			trafficDao_.saveOrUpdate(listOfFeedsWithTraffic);
		}
		else{
			System.out.println("Feeds are null");
		}
	}
	
	/**
	 * If there are feeds with new comments, we get those new comments.
	 * The reason is to search into them for keywords
	 * @param feedID
	 * @return
	 */
	public CommentList getNewComments(final String feedID){
		CommentList comments_=new CommentList();
		if(null!=feedID){
			String numberOfNewComments = feedsWithTraffic.get(feedID);
			//System.out.println("Number of new comments: "+numberOfNewComments);
			
			for(int i=0;i<Integer.parseInt(numberOfNewComments);i++){
				Feed feed = super.getFeeds().getFeedByID(feedID);
				int indexOfNewComment = feed.getNumberOfComments_()-(i+1);
				//System.out.println("Index of new comment: "+indexOfNewComment);
				Comment comment = feed.getComments_().getComment(indexOfNewComment);
				
				//System.out.println(comment.getId_());
				comments_.addComment(comment);
			}
		}
		return comments_;
	}
	
	private void clearHotFeeds(){
		hotFeeds_.clear();
	}
	
	public Set<String> getCurrentFeedsWithCommentTraffic(){
		return hotFeeds_;
	}
	
	public boolean thereAreFeedsWithCommentTraffic(){
		boolean thereIsTraffic=false;
		if(this.getCurrentFeedsWithCommentTraffic().size()>0){
			thereIsTraffic=true;
		}
		return thereIsTraffic;
	}
	
/*	*//**
	 * Creates a list of comments from different feeds that are the new comments and 
	 * in which we want to search for keywords
	 *//*
	public CommentList getCommentListForSearching(){
		CommentList comments = new CommentList();
		Set feeds = this.getCurrentFeedsWithCommentTraffic();
		for(int i=0;i<feeds.size();i++){
			comments.
		}
	}*/
}
