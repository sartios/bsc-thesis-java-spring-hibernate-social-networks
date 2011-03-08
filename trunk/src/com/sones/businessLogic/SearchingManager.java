package com.sones.businessLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for managing the results
 * of the searching actions. 
 * 
 * @author Sartios
 *
 */
public class SearchingManager {
	
	private FeedList feeds_;
	
	private KeywordList keywords_;
	
	public SearchingManager(final FeedList feeds,KeywordList keywords){
		this.feeds_=feeds;
		this.keywords_=keywords;
	}
	
	public SearchingManager(final FeedList feeds){
		this.feeds_=feeds;
		this.keywords_=null;
	}
	
	/**
	 * Searches for the keywords into the feeds. If a feed doesn't have keywords
	 * we delete the feed. If it contains keywords, we add its ID into the list.
	 * @param feeds
	 * @param keywords
	 * @return IDs of the feeds which contain keyword
	 */
	public ResultList search(){
		ResultList results = new ResultList(getKeywords());
		Feed feed;
		Keyword keyword;
		if(listsAreOk(feeds_,keywords_)){
			while(null!=(feed = feeds_.getFeed())){
				while(null!=(keyword=keywords_.getKeyword())){
					if(feed.find(keyword.getValue())||feed.searchIntoCommentsFor(keyword.getValue())){
						results.addID(keyword.getValue(), feed.getID());
						keyword.increaseNumberOfAppears();
					}
				}
				keywords_.reset();
			}
		}
		return results;
	}
	
	/**
	 * Searches for the keywords into the feeds. If a feed contains keyword
	 * we save the ID of the feed into keyword's ID list
	 */
	public KeywordList search_new(){
		Feed feed;
		if(listsAreOk(feeds_,keywords_)){
			while(null!=(feed = feeds_.getFeed())){
				for(int i=0;i<keywords_.getSize();i++){
					if(feed.find(keywords_.getKeyword(i).getValue())||feed.searchIntoCommentsFor(keywords_.getKeyword(i).getValue())){
						keywords_.getKeyword(i).addID(feed.getID());
						keywords_.getKeyword(i).increaseNumberOfAppears();
					}
				}
			}
		}
		return keywords_;
	}
	
	/**
	 * Check if the lists agree with the security issues.
	 * @param feeds
	 * @param keywords
	 * @return true if are ok
	 */
	private boolean listsAreOk(final FeedList feeds,final KeywordList keywords){
		if((null!=feeds) && (null!=keywords)){
			if((!feeds.isEmpty())&&(!keywords.isEmpty())){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Extracts the values from keywords and puts them into a list
	 * @return keyword's value
	 */
	private List<String> getKeywords(){
		List<String> keywords = new ArrayList<String>();
		Keyword keyword;
		while(null!=(keyword=this.keywords_.getKeyword())){
			keywords.add(keyword.getValue());
		}
		keywords_.reset();
		return keywords;
	}
	
	/**
	 * Searches for feeds which their number of comments are equal or more than a specified
	 * comment number
	 * @param numberOfComments
	 */
	public List<String> getFeedsWithCommentNumber(int numberOfComments){
		if((numberOfComments>0)&&(null!=feeds_)){
			List<String> feedIDs = new ArrayList<String>();
			Feed feed = feeds_.getFeed();
			while(feed!=null){
				if(feed.getNumberOfComments()>=numberOfComments){
					feedIDs.add(feed.getID());
				}
				feed = feeds_.getFeed();
			}
			return feedIDs;
		}
		return null;
	}
}
