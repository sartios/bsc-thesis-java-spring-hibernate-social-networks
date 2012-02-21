package com.sones.facebook.model.source;

/**
 * Contains the property names and lengths for the {@link Group} entity.
 * @author sartios.sones@gmail.com.
 *
 */
public final class GroupConstants 
{
	/**
	 * The table name.
	 */
	public static final String TABLE_NAME = "GROUPS";
	
	/**
	 * The name of the ID property.
	 */
	public static final String PROPERTY_ID = "GROU_ID";
	
	/**
	 * The name of the name property.
	 */
	public static final String PROPERTY_NAME = "GROU_NAME";

	/**
	 * The name of the description property.
	 */
	public static final String PROPERTY_DESCRIPTION = "GROU_DESCRIPTION";
	
	/**
	 * The name of the updated time property.
	 */
	public static final String PROPERTY_UPDATED_TIME = "GROU_UPDATED_TIME";
	
	/**
	 * The name of the link property.
	 */
	public static final String PROPERTY_LINK = "GROU_LINK";
	
	/**
	 * The length of the ID property.
	 */
	public static final int LENGTH_ID = 20;
	
	/**
	 * The length of the name property.
	 */
	public static final int LENGTH_NAME = 50;
	
	/**
	 * The length of the category property.
	 */
	public static final int LENGTH_DESCRIPTION = 255;
	
	/**
	 * The length of the link url.
	 */
	public static final int LENGTH_LINK = 255;
}
