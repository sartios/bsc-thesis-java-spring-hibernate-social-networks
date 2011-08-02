package com.sones.businessLogic.Facebook.Feeds;

import com.sones.businessLogic.Facebook.Feeds.FacebookFeedList;
import com.sones.businessLogic.Facebook.Rest.FacebookRestHandlerFactory;
import com.sones.businessLogic.Facebook.Rest.IFacebookRestHandler;

public class FacebookFeedListFactory {

	private static FacebookFeedListFactory instance_=null;
	private IFacebookRestHandler handler_=null;
	
	private FacebookFeedListFactory(){
		
	}
	
	public static FacebookFeedListFactory getInstance(){
		if(null==instance_){
			instance_=new FacebookFeedListFactory();
		}
		return instance_;
	}
	
	public FacebookFeedList getFeedList(final String typeOfList,final String userID,final String token,final String date){
		if(null!=typeOfList){
			if(typeOfList.equals("keywordSearch")){
				return this.getFeedListWithFeedsHavingFullContent(token, userID, date);
			}
			if(typeOfList.equals("noSearch")){
				return this.getFeedListWithSimpleFeeds(token, userID, date);
			}
		}
		return null;
	}
	
	private FacebookFeedList getFeedListWithFeedsHavingFullContent(final String token,final String userID,final String date){
		handler_ = FacebookRestHandlerFactory.getInstance().getFacebookRestHandler("completeGraph");
		return 	handler_.getFeedsSinceDate(userID, token, date);
	}
	
	private FacebookFeedList getFeedListWithSimpleFeeds(final String token,final String userID,final String date){
		handler_ = FacebookRestHandlerFactory.getInstance().getFacebookRestHandler("simpleGraph");
		return 	handler_.getFeedsSinceDate(userID, token, date);
	}
}
