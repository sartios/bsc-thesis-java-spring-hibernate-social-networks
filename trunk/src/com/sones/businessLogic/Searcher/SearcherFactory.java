package com.sones.businessLogic.Searcher;

import java.util.ArrayList;
import java.util.List;

public class SearcherFactory {

	private static SearcherFactory instance_=null;
	
	private SearcherFactory(){}
	
	public static SearcherFactory getInstance(){
		if(null==instance_){
			instance_ = new SearcherFactory();
		}
		return instance_;
	}
	
	/**
	 * Reads the type of searcher we want. Creates and returns it.
	 * If type is the word keyword, we create a KeywordSearcher to search for keywords
	 * If type is the word hot, we create a HotFeedSearcher to search for hot feeds.
	 * @param type
	 * @return AbstractSearcher
	 */
	public static AbstractSearcher getSearcher(final String type){
		if(type.equals("keyword")){
			return new KeywordSearcher();
		}
		else if(type.equals("hotFeed")){
			return new CommentTrafficSearcher();
		}
		else if(type.equals("all")){
			List<AbstractSearcher> searchers = new ArrayList<AbstractSearcher>();
			searchers.add(new KeywordSearcher());
			searchers.add(new CommentTrafficSearcher());
			return new MultiSearcher(searchers);
		}
		return null;
	}
}
