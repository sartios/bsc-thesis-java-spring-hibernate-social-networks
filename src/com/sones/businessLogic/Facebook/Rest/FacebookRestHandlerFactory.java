package com.sones.businessLogic.Facebook.Rest;

public class FacebookRestHandlerFactory {

	private static FacebookRestHandlerFactory instance_=null;
	
	private FacebookRestHandlerFactory(){
	}
	
	public static FacebookRestHandlerFactory getInstance(){
		if(null==instance_){
			instance_=new FacebookRestHandlerFactory();
		}
		return instance_;
	}
	
	/**
	 * Creates a Facebook Rest Handler specified by the type
	 * Type options :
	 * 1. <b>completeGraph</b> returns a rest handler who implements the Graph API
	 * 2. <b>simpleGraph</b> returns a rest handler who implements the Graph API but the feeds are the simple
	 * @param type
	 * @return
	 */
	public IFacebookRestHandler getFacebookRestHandler(final String type){
		if(null!=type){
			if(type.equals("completeGraph")){
				return new FacebookRestHandler("complete");
			}
			if(type.equals("simpleGraph")){
				return new FacebookRestHandler("simple");
			}
		}
		return null;
	}
}
