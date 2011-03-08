package com.sones.businessLogic.Facebook;

public class FacebookGroup {

	/**
	 * Group's ID
	 */
	private final String ID_;
	
	/**
	 * The name of the group
	 */
	private final String NAME_;
	
	/**
	 * A brief description of the group
	 */
	private final String DESCRIPTION_;
	
	/**
	 * Constructor
	 */
	public FacebookGroup(final String ID, final String NAME,final String Description){
		this.ID_ = ID;
		this.NAME_ = NAME;
		this.DESCRIPTION_ = Description;
	}
	
	public String getID(){
		return this.ID_;
	}
	
	public String getName(){
		return this.NAME_;
	}
	
	public String getDescription(){
		return this.DESCRIPTION_;
	}
}