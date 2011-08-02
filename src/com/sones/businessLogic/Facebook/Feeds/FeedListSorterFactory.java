package com.sones.businessLogic.Facebook.Feeds;

public class FeedListSorterFactory {

	private static FeedListSorterFactory instance_;
	
	private FeedListSorterFactory(){	
	}
	
	public static FeedListSorterFactory getInstance(){
		if(null==instance_){
			instance_=new FeedListSorterFactory();
		}
		return instance_;
	}
	
	public IFeedListSorter getSorter(final String type){
		if(type!=null){
			if(type.equals("quicksort")){
				return new QuickSortFeedListSorter();
			}
		}
		return null;
	}
}
