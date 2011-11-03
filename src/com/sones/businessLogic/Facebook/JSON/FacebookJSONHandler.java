package com.sones.businessLogic.Facebook.JSON;



import net.sf.ezmorph.MorphException;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.DynaBean;
import org.apache.log4j.Logger;

import com.sones.businessLogic.Facebook.FeedManager.FacebookFeedList;
import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeeds;

public class FacebookJSONHandler implements IFacebookJsonHandler{
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

	private	IFacebookFeedFactory	_feedFactory	=	null;
	private	Logger	_logger;
	
	public	FacebookJSONHandler()
	{
		_feedFactory	=	new	FacebookFeedFactoryFromDynaBean();
		_logger	=	Logger.getLogger( FacebookJSONHandler.class );
	}
	
	public IFacebookFeeds GetFeeds(final String jsonString){
		
		IFacebookFeeds	feeds	=	new	FacebookFeedList();
		
		try
		{
			this.object_	=	JSONObject.fromObject(jsonString);
			this.jsonArray_	=	this.object_.getJSONArray("data");
			int	arrayDimensions[]	=	JSONArray.getDimensions(jsonArray_);
			DynaBean	beanObject;
		
			for( int i = 0; i < arrayDimensions[0]; i++ )
			{
				beanObject	=	(DynaBean)	JSONObject.toBean( jsonArray_.getJSONObject(i) );
				feeds.Add( _feedFactory.GetFeed( beanObject ) );
			}
		}
		catch (JSONException ex) 
		{
		}
		if( null == feeds )
		{
			_logger.error( "Feeds are null" );		
		}
		return feeds;
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
