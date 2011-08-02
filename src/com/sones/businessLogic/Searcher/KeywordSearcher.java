package com.sones.businessLogic.Searcher;

import java.util.List;

import com.sones.businessLogic.SocialNetworkUser;
import com.sones.businessLogic.Facebook.Rest.FacebookRestHandler;
import com.sones.businessLogic.Facebook.Source.FacebookUser;
import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.Feeds.FacebookFeedList;
import com.sones.businessLogic.Facebook.StatusMessage;
import com.sones.businessLogic.KeywordManager.Keyword;
import com.sones.businessLogic.KeywordManager.Keyword_old;
import com.sones.businessLogic.KeywordManager.KeywordList;
import com.sones.dao.KeywordDao;
import com.sones.dao.KeywordListDao;
import com.sones.dao.KeywordSearchResultDao;

/**
 * The purpose of this class is to searching into feeds
 * for the given keywords and saving the results.
 * @author Sartios
 *
 */
public class KeywordSearcher implements ISearcher{

	/**
	 * We create the keyword list in the creation of the object.
	 * During the life of it, the keyword list maybe contains more
	 * keywords. Maybe we should add an update method, to update the
	 * list with the new keywords.
	 */
	private KeywordList keywords_;
	
	/**
	 * 
	 */
	private SocialNetworkUser user_;
	
	/**
	 * 
	 * @param user
	 */
	public KeywordSearcher(final SocialNetworkUser user){
		user_=user;
		keywords_=new KeywordList();
	}
	
	/**
	 * 
	 */
	@Override
	public void search() {
		KeywordList keywords = user_.getKeywords("database");
		user_.resetSources();
		FacebookFeedList feeds;
		if(keywords.isEmpty()==false){
			System.out.println("User has create "+keywords.getSize()+" keywords");
			while(user_.hasMoreSources()){
				feeds = user_.getNextSourceFeeds("keywordSearch");
				System.out.println("This source has "+feeds.getSize()+" feeds");
				for(int i=0;i<keywords.getSize();i++){
					System.out.println("Keyword value is "+keywords.getKeyword(i).getValue_());
					List<Feed> results = feeds.getFeedsThatContainTheKeyword(keywords.getKeyword(i).getValue_());
					System.out.println(results.size()+" feeds contain the keyword "+keywords.getKeyword(i).getValue_());
					addResults(keywords.getKeyword(i),results);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param keyword
	 * @param feeds
	 */
	private void addResults(final Keyword keyword, final List<Feed> feeds){
		for(int i=0;i<feeds.size();i++){
			keyword.addFeed(feeds.get(i));
		}
		keywords_.addKeyword(keyword);
	}
	
	/**
	 * 
	 * @return
	 */
	public KeywordList getResults(){
		return keywords_;
	}
}
