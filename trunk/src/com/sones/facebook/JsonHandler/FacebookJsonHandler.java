package com.sones.facebook.JsonHandler;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.beanutils.DynaBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.sones.facebook.JsonHandler.Factory.IWallFeedFactory;
import com.sones.facebook.JsonHandler.Factory.WallFeedFactory;
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
	
	@Override
	public Iterable<WallFacebookPostCreateDto> GetWallPosts(String jsonString)
	{
		IWallFeedFactory factory = new WallFeedFactory();
		
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
		}
		
		return	posts;
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
