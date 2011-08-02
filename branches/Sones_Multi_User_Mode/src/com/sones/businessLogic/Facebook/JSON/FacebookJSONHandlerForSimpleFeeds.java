package com.sones.businessLogic.Facebook.JSON;

import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.DynaBean;

import com.sones.businessLogic.Facebook.Comment;
import com.sones.businessLogic.Facebook.CommentList;
import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.FeedFactory;
import com.sones.businessLogic.Facebook.Source.FacebookFriend;
import com.sones.businessLogic.Facebook.Source.FacebookFriendList;
import com.sones.businessLogic.Facebook.Source.FacebookGroupList;

public class FacebookJSONHandlerForSimpleFeeds implements IFacebookJsonHandler{

	private JSONArray jsonArray_;

	private JSONObject object_;
	
	@Override
	public CommentList getAllComments(String jsonString) {
		return null;
	}

	@Override
	public Comment getComment(String jsonString, int index) {
		return null;
	}

	@Override
	public FacebookFriend getFacebookUser(String jsonString) {
		return null;
	}

	@Override
	public Feed getFeed(String jsonString) {
		DynaBean beanObject=null;
		try{
			this.object_ = JSONObject.fromObject(jsonString);
			beanObject = (DynaBean) JSONObject.toBean(this.object_);
		}
		catch (JSONException ex) {			
		}
		Feed feed = FeedFactory.getInstance().getSimpleFeed(beanObject);
		return feed;
	}

	@Override
	public Set<Feed> getFeeds(String jsonString) {
		Set<Feed> feeds = new HashSet<Feed>();
		try{
			this.object_ = JSONObject.fromObject(jsonString);
			this.jsonArray_ = this.object_.getJSONArray("data");
			int	arrayDimensions[] = JSONArray.getDimensions(jsonArray_);
			DynaBean	beanObject;
		
			for(int i=0;i<arrayDimensions[0];i++){
				beanObject = (DynaBean) JSONObject.toBean(jsonArray_.getJSONObject(i));
				feeds.add(FeedFactory.getInstance().getSimpleFeed(beanObject));
			}
		}
		catch (JSONException ex) {
		}
		return feeds;
	}

	@Override
	public FacebookFriendList getFriends(String jsonString) {
		return null;
	}

	@Override
	public FacebookGroupList getGroups(String jsonString) {
		return null;
	}

}
