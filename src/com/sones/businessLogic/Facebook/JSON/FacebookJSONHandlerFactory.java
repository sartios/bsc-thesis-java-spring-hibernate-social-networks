package com.sones.businessLogic.Facebook.JSON;

public class FacebookJSONHandlerFactory {

	private static FacebookJSONHandlerFactory instace_=null;
	
	private FacebookJSONHandlerFactory(){
	}
	
	public static FacebookJSONHandlerFactory getInstance(){
		if(null==instace_){
			instace_=new FacebookJSONHandlerFactory();
		}
		return instace_;
	}
	
	/**
	 * Returns an IFacebookJSONHandler
	 * Type options :
	 * 1. <b>complete</b> returns a JSON handler which extracts complete feeds
	 * 2. <b>simple</b> returns a JSON handler which extracts very simple feeds
	 * @param type
	 * @return
	 */
	public IFacebookJsonHandler getFacebookJSONHandler(final String type){
		if(null!=type){
			if(type.equals("complete")){
				return new FacebookJSONHandlerForCompleteFeeds();
			}
			if(type.equals("simple")){
				return new FacebookJSONHandlerForSimpleFeeds();
			}
		}
		return null;
	}
}
