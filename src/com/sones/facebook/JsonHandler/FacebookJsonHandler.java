package com.sones.facebook.JsonHandler;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.beanutils.DynaBean;
import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.sones.facebook.JsonHandler.Factory.FacebookFriendFactory;
import com.sones.facebook.JsonHandler.Factory.IFacebookFriendFactory;
import com.sones.facebook.JsonHandler.Factory.IPublicPlaceFactory;
import com.sones.facebook.JsonHandler.Factory.IWallFeedFactory;
import com.sones.facebook.JsonHandler.Factory.PublicPlaceFactory;
import com.sones.facebook.JsonHandler.Factory.WallFeedFactory;
import com.sones.facebook.downloader.model.FacebookFriend;
import com.sones.facebook.placemanager.model.Place;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallFacebookPostCreateDto;

public class FacebookJsonHandler	implements	IFacebookJsonHandler
{
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
	
	private	IWallFeedFactory factory;
	private	IPublicPlaceFactory placeFactory;
	private IFacebookFriendFactory friendFactory;
	private final Logger _LOGGER; 
	
	public FacebookJsonHandler()
	{
		_LOGGER = Logger.getLogger( FacebookJsonHandler.class );
		factory = new WallFeedFactory();
		placeFactory = new PublicPlaceFactory();
		friendFactory = new FacebookFriendFactory();
	}
	
	@Override
	public Iterable<WallFacebookPostCreateDto> GetWallPosts(String jsonString)
	{
		Set<WallFacebookPostCreateDto>	posts	=	new	HashSet<WallFacebookPostCreateDto>();
		try
		{
			this.object_	=	JSONObject.fromObject(jsonString);
			this.jsonArray_	=	this.object_.getJSONArray("data");
			int	arrayDimensions[]	=	JSONArray.getDimensions(jsonArray_);
			DynaBean	beanObject;
		
			for( int i = 0; i < arrayDimensions[0]; i++ )
			{
				beanObject	=	(DynaBean)	JSONObject.toBean( jsonArray_.getJSONObject(i) );
				posts.add(factory.getPost(beanObject));
			}
		}
		catch (JSONException ex) 
		{
			_LOGGER.error( "An error occured while trying to extract places from json string" );
		}
		
		return	posts;
	}
	
	@Override
	public Iterable<Place> GetPublicPlaces(String jsonString) 
	{
		Set< Place > places = new HashSet<Place>();
		try
		{
			this.object_	=	JSONObject.fromObject(jsonString);
			this.jsonArray_	=	this.object_.getJSONArray("data");
			int	arrayDimensions[]	=	JSONArray.getDimensions(jsonArray_);
			DynaBean	beanObject;
		
			for( int i = 0; i < arrayDimensions[0]; i++ )
			{
				beanObject	=	(DynaBean)	JSONObject.toBean( jsonArray_.getJSONObject(i) );
				Place place = placeFactory.GetPublicPlace( beanObject );
				if( place != null )
				{
					places.add( place );
				}
			}
		}
		catch (JSONException ex) 
		{
		}
		return places;
	}
	
	@Override
	public Iterable<FacebookFriend> GetFacebookFriends(String jsonString)
	{
		Set<FacebookFriend> friends = new HashSet<FacebookFriend>();
		try
		{
			this.object_	=	JSONObject.fromObject(jsonString);
			this.jsonArray_	=	this.object_.getJSONArray("data");
			int	arrayDimensions[]	=	JSONArray.getDimensions(jsonArray_);
			DynaBean	beanObject;
		
			for( int i = 0; i < arrayDimensions[0]; i++ )
			{
				beanObject	=	(DynaBean)	JSONObject.toBean( jsonArray_.getJSONObject(i) );
				FacebookFriend friend = friendFactory.GetFriend( beanObject );
				if( friend != null )
				{
					friends.add( friend );
				}
			}
		}
		catch (JSONException ex) 
		{
		}
		return friends;
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
    
    private	void initializeObjectAndArray( String jsonString )
    {
		this.object_	=	JSONObject.fromObject(jsonString);
		this.jsonArray_	=	this.object_.getJSONArray("data");
    }
}
