package com.sones.businessLogic.Searcher;

import com.sones.businessLogic.Facebook.FacebookRestHandler;
import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.FeedList;
import com.sones.businessLogic.Facebook.StatusMessage;
import com.sones.businessLogic.KeywordManager.Keyword;
import com.sones.businessLogic.KeywordManager.KeywordList;
import com.sones.dao.KeywordDao;

/**
 * The purpose of this class is to searching into feeds
 * for the given keywords and saving the results.
 * @author Sartios
 *
 */
public class KeywordSearcher extends AbstractSearcher{

	/**
	 * We create the keyword list in the creation of the object.
	 * During the life of it, the keyword list maybe contains more
	 * keywords. Maybe we should add an update method, to update the
	 * list with the new keywords.
	 */
	private KeywordList keywords_;
	private KeywordDao dao_;
	private FacebookRestHandler handler_;
	
	public KeywordSearcher(){
		dao_ = new KeywordDao();
		keywords_ = dao_.getKeywordList();
		handler_ = new FacebookRestHandler();
	}
	
	@Override
	public void search() {
	}
	
	public void search(FeedList feeds){
		int size = feeds.getSize();
		
		System.out.println(size);
		
		for(int feedIndex=0;feedIndex<size;feedIndex++){
			String feedID = feeds.getFeedID(feedIndex);
			System.out.println(feedID);
			
			Feed feed = handler_.getFeed(feedID);
			if(feed.isEmpty()){
				continue;
			}
			//System.out.println(((StatusMessage)feed).getMessage());
			
			Keyword keyword;
			while(null!=(keyword=keywords_.getKeyword())){			
				String keywordValue = keyword.getValue();
				
			//System.out.println(keywordValue);

				boolean keyExists=feed.find(keywordValue);
				if(keyExists){
					
					//System.out.println(keyExists);
					
					keyword.addID(feed);
					dao_.saveOrUpdate(keyword);
				}				
			}
			keywords_.reset();
		}
	}
}
