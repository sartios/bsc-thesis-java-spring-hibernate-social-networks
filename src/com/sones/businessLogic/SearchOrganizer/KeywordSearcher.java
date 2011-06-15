package com.sones.businessLogic.SearchOrganizer;

import com.sones.businessLogic.Facebook.FacebookRestHandler;
import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.FeedList;
import com.sones.businessLogic.KeywordManager.Keyword;
import com.sones.businessLogic.KeywordManager.KeywordList;
import com.sones.dao.KeywordDao;

public class KeywordSearcher extends AbstractSearcher{

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
		
		FeedList feeds_ = super.getFeeds();
		int size = feeds_.getSize();
		
	//	System.out.println(size);
		
		for(int feedIndex=0;feedIndex<size;feedIndex++){
			Feed feed = handler_.getFeed(feeds_.getFeedID(feedIndex));
			
			/*System.out.println(((StatusMessage)feed).getMessage());*/
			
			Keyword keyword;
			while(null!=(keyword=keywords_.getKeyword())){			
				String keywordValue = keyword.getValue();
				
		//	System.out.println(keywordValue);

				boolean keyExists=feed.find(keywordValue);
				if(keyExists){
					
			//		System.out.println(keyExists);
					
					keyword.addID(feed);
					dao_.saveOrUpdate(keyword);
				}				
			}
			keywords_.reset();
		}
	}
}
