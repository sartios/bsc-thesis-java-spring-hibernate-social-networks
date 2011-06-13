package com.sones.businessLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FeedsForDisplay {
	
	private Map<String, String> facebookFeeds;
	
	public FeedsForDisplay(){
		facebookFeeds = new HashMap<String, String>();
	}
	
	public void setFeed(final String feedID,final String type){
		if(typeIsLegal(type)){
			facebookFeeds.put(feedID, type);
		}
	}
	
	public List<String> getFacebookFeedsFromKeywordSearch(){
		List<String> feeds = new ArrayList<String>();
		Set<String> keyes  = facebookFeeds.keySet();
		for(int i=0;i<facebookFeeds.size();i++){
			String currentKey = (String) keyes.toArray()[i];
			if(facebookFeeds.get(currentKey)=="keyword"){
				feeds.add(currentKey);
			}
		}
		return feeds;
	}
	
	public List<String> getFacebookFeedsFromHotFeedSearch(){
		List<String> feeds = new ArrayList<String>();
		Set<String> keyes  = facebookFeeds.keySet();
		for(int i=0;i<facebookFeeds.size();i++){
			String currentKey = (String) keyes.toArray()[i];
			if(facebookFeeds.get(currentKey)=="hotFeed"){
				feeds.add(currentKey);
			}
		}
		return feeds;
	}
	
	private boolean typeIsLegal(final String type){
		return true;
	}
}
