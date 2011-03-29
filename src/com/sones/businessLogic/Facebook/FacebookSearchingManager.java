package com.sones.businessLogic.Facebook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sones.businessLogic.Feed;
import com.sones.businessLogic.FeedList;
import com.sones.businessLogic.Keyword;
import com.sones.dao.FeedDao;
import com.sones.dao.KeywordDao;
import com.sones.dao.TokenDao;

public class FacebookSearchingManager {
	
	private FacebookRestHandler handler=null;
	private FeedDao feedDao=null;
	private TokenDao tokenDao = null;
	private FacebookToken token = null;

	public FacebookSearchingManager(){
		handler = new FacebookRestHandler();
		feedDao = new FeedDao();
		tokenDao = new TokenDao();
		token = new FacebookToken();
		token=(FacebookToken) tokenDao.findAll().get(0);
	}
	
	/**
	 * Downloads from facebook sources feeds and loads from database the old.
	 * Then compares the distance between the number of comment and if it is
	 * what we want, then puts into a List the ID. In the end, it returns the
	 * list and updates the number of comments from the feeds.
	 * 
	 * @param sources facebook's id
	 * @param limit of the distance between the number of comments
	 * @return Set of feed ids whose comment distance is > of limit
	 */
	public Set<String> getPopularFeeds(FacebookSearchingList sources,final int limit){
		
		Set<String> hotFeeds = new HashSet<String>();
		String userID;
		
		while(null!=(userID=sources.getID())){
			FeedList newUserFeeds = handler.getFeeds(userID, token.getToken());
			int size = newUserFeeds.getSize();
			for(int i=0;i<size;i++){
				Feed newFeed = newUserFeeds.getFeed(i);
				String newFeedID=newUserFeeds.getFeed(i).getId_();
				Feed oldFeed = feedDao.find(newFeed.getId_());
				//System.out.println(numOfCom.getFromId_());
				if(null==oldFeed){
					feedDao.saveOrUpdate(newUserFeeds.getFeed(i));
				}
				else{
					int nf_numOfCom = newFeed.getNumberOfComments_();
					int of_numOfCom = oldFeed.getNumberOfComments_();
					int distance = nf_numOfCom - of_numOfCom;
					System.out.println(distance);
					if(limit<=distance){
						hotFeeds.add(newUserFeeds.getFeed(i).getId_());
					}
					feedDao.saveOrUpdate(newUserFeeds.getFeed(i));

				}
			}
		}
		
		return hotFeeds;
	}
	
	public void findKeywordsInFeeds(FacebookSearchingList sources){
		
		KeywordDao keywordDao = new KeywordDao();
		String userID;
		
		List<Keyword> keywords = keywordDao.findAll();
		
		while(null!=(userID=sources.getID())){
			FeedList userFeeds = handler.getFeeds(userID, token.getToken());
			feedDao.saveUserFeeds(userFeeds);
			int size = userFeeds.getSize();

			for(int feedIndex=0;feedIndex<size;feedIndex++){
				String feedID = userFeeds.getFeedID(feedIndex);
				Feed feed = handler.getFeed(feedID, token.getToken());
				for(int keywordIndex=0;keywordIndex<keywords.size();keywordIndex++){	
					
					Keyword keyword = (Keyword)keywords.get(keywordIndex);
					String keywordValue = keyword.getValue();
					System.out.println(keywordValue);
					boolean keyExists = false;//feed.find(keywordValue);
					System.out.println(keyExists);
					if(keyExists){
						keyword.addID(userFeeds.getFeed(feedIndex));
						keywordDao.saveOrUpdate(keyword);
					}
					
				}
			}
		}
	}
}
