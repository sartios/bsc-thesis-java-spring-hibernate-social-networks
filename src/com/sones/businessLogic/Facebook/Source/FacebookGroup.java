package com.sones.businessLogic.Facebook.Source;

public class FacebookGroup {

	/**
	 * Group's ID
	 */
	private String id;
	
	/**
	 * The name of the group
	 */
	private String name;
	
	/**
	 * A brief description of the group
	 */
	private String description;
	
	public FacebookGroup(){}
	
	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Constructor
	 */
	public FacebookGroup(final String ID, final String NAME,final String Description){
		this.id = ID;
		this.name = NAME;
		this.description = Description;
	}
	
	public String getID(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
}