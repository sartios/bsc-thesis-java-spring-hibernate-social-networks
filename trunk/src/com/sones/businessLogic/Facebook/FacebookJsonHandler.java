package com.sones.businessLogic.Facebook;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.beanutils.DynaBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sones.businessLogic.Feed;
import com.sones.businessLogic.FeedFactory;

/**
 * Includes functionality for decode JSON format files.
 * Uses DynaBeans and Json-lib
 *
 * <b><i>DynaBean</i></b>
 *
 * DynaBean is a Java object that supports properties whose names and data types,
 * as well as values, maybe dynamically modified.
 * @see <a href src="http://commons.apache.org/beanutils/api/org/apache/commons/beanutils/DynaBean.html">DynaBean</as>
 *
 * <b><i>Json-lib</i></b>
 *
 * Json-lib is a java library for transforming beans, maps, collections, java arrays
 * and XML to JSON and back again to beans and DynaBeans
 * @see <a href src="http://json-lib.sourceforge.net/apidocs/net/sf/json/JSON.html">JSON Library</a>
 */
public class FacebookJsonHandler {
	
	/**
	 * JsonArray, the external form is a string wrapped in square brackets
	 * with commas between the values. The internal form is an object
	 * having get() and opt() method for accessing the value by index
     * and put() method for adding or replacing values.
	 */
	private JSONArray jsonArray_;
	
	 /**
     * JSON object is an unordered collection of name/value pairs.
     * The external form is a string wrapped in curly braces with colons
     * between the names and the values, and commas between the values
     * and names.
     * {
     *  "name" : "Couper"
     * }
     * The internal form is an object having get() and opt() methods
     * for accessing the values by name, and put() methods
     * for adding or replacing values by name.
     */
	private JSONObject object_;

	/**
	 * Extracts the friends from a json string and returns them
	 * @param jsonString
	 * @return vector with facebook friends
	 */
	public FacebookFriendList getFriends(final String jsonString){

		this.object_ = JSONObject.fromObject(jsonString);
		this.jsonArray_ = this.object_.getJSONArray("data");
		int	arrayDimensions[] = JSONArray.getDimensions(jsonArray_);
		DynaBean	beanObject;
		FacebookFriendList friendsList = new FacebookFriendList();
		
		for(int i=0;i<arrayDimensions[0];i++){
			beanObject = (DynaBean) JSONObject.toBean(jsonArray_.getJSONObject(i));
			FacebookFriend friend = new FacebookFriend(beanObject.get("name").toString(), beanObject.get("id").toString());
			friendsList.addFriend(friend);
		}
		return friendsList;
	}
	
	/**
	 * Returns the feeds
	 * @param jsonString
	 *//*
	public FeedList getFeeds(final String jsonString){
		
		this.object_ = JSONObject.fromObject(jsonString);
		this.jsonArray_ = this.object_.getJSONArray("data");
		int	arrayDimensions[] = JSONArray.getDimensions(jsonArray_);
		DynaBean	beanObject;
		FeedList 	feeds = new FeedList();
		
		for(int i=0;i<arrayDimensions.length;i++){
			beanObject = (DynaBean) JSONObject.toBean(jsonArray_.getJSONObject(i));
			FacebookFriend from = new FacebookFriend(reCaster(beanObject.get("from")).get("name").toString(), reCaster(beanObject.get("from")).get("id").toString());
			feeds.addFeed(new Feed(beanObject.get("id").toString(),from, beanObject.get("created_time").toString()));
		}
		return feeds;
	}
	
	*/
	
	/**
	 * Extracts the facebook groups and returns them into a FacebookGroupList
	 * @param jsonString
	 * @return FacebookGroupList
	 */
	public FacebookGroupList getGroups(final String jsonString){
		this.object_ = JSONObject.fromObject(jsonString);
		this.jsonArray_ = this.object_.getJSONArray("data");
		int arrayDimensions[] = JSONArray.getDimensions(jsonArray_);
		DynaBean beanObject;
		FacebookGroupList groups = new FacebookGroupList();
		
		for(int i=0;i<arrayDimensions[0];i++){
			beanObject = (DynaBean) JSONObject.toBean(jsonArray_.getJSONObject(i));
			groups.addGroup(FacebookGroupFactory.getInstance().getGroup(beanObject));
		}
		return groups;
	}
	
	/**
	 * Extracts simple feeds from a json string and returns them into a set.
	 * In this version of the method, we don't check we parameters.
	 * 
	 * @param jsonString from which we will extract the feeds.
	 * @return set with the feeds.
	 * 
	 * @version 1.0
	 */
	public Set<Feed> getFeeds(final String jsonString){
		Set<Feed> feeds = new HashSet<Feed>();
		
		this.object_ = JSONObject.fromObject(jsonString);
		this.jsonArray_ = this.object_.getJSONArray("data");
		int	arrayDimensions[] = JSONArray.getDimensions(jsonArray_);
		DynaBean	beanObject;
		
		for(int i=0;i<arrayDimensions[0];i++){
			beanObject = (DynaBean) JSONObject.toBean(jsonArray_.getJSONObject(i));
			feeds.add(FeedFactory.getInstance().getSimpleFeed(beanObject));
		}
		return feeds;
	}
	
	/**
	 * Extracts the feed from a JSON string
	 * @param jsonString
	 * @return feed
	 */
	public Feed getFeed(final String jsonString){
		this.object_ = JSONObject.fromObject(jsonString);
		DynaBean beanObject;
		beanObject = (DynaBean) JSONObject.toBean(this.object_);
		return FeedFactory.getInstance().getFeed(beanObject);
	}
	
	/**
	 * Returns the facebook friend
	 * @param jsonString
	 * @return
	 */
	public FacebookFriend getFacebookUser(final String jsonString){
		this.object_ = JSONObject.fromObject(jsonString);
		FacebookFriend friend =null;
		DynaBean beanObject = (DynaBean) JSONObject.toBean(this.object_);
		friend = new FacebookFriend(beanObject.get("name").toString(), beanObject.get("id").toString());
		return friend;
	}
	
    /**
     * Casts an Object to DynaBean
     * @param aBean
     * @return DynaBean
     */
    public static DynaBean reCaster(Object aBean){
            DynaBean aDynaBean = (DynaBean) aBean;
            return aDynaBean;

    }
}
