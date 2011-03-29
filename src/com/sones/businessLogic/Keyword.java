package com.sones.businessLogic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Keyword {

	private long id;
	/**
	 * The keyword's value
	 */
	private String value;
	
	/**
	 * Keyword's times of appear
	 */
	private int numberOfAppears;
	
	/**
	 * The threshold of keyword's appearing
	 */
	private int threshold;
	
	/**
	 * The list of IDs that contain this keyword
	 */
	private Set<Feed> ids;

	public Keyword(){
		this.value = new String();
		this.numberOfAppears = 0;
		this.threshold = -1;
		this.ids = new HashSet<Feed>();
	}
	
	/**
	 * Constructor with arguments
	 * @param value
	 */
	public Keyword(final String value){
		if(null!=value){
			this.value = value.toLowerCase();
		}
		this.numberOfAppears = 0;
		this.threshold = -1;
		this.ids = new HashSet<Feed>();
	}
	
	/**
	 * Constructor with arguments
	 * @param value
	 */
	public Keyword(final String value,final int threshold){
		if(null!=value){
			this.value = value.toLowerCase();
		}
		if(threshold<0){
			this.threshold = -1;
		}
		this.numberOfAppears = 0;
		this.threshold = threshold;
		this.ids = new HashSet<Feed>();
	}
	
	/**
	 * @return value of the keyword
	 */
	public String getValue(){
		return this.value.toString();
	}
	
	public void setValue(final String value){
		this.value = value.toLowerCase();
	}
	
	public int getNumberOfAppears(){
		return this.numberOfAppears;
	}
	
	/**
	 * @return true if the threshold is equal of smaller than number of appears
	 */
	public boolean canBeNotifiable(){
		return threshold <= numberOfAppears;
	}
	
	/**
	 * Increase the number of appears
	 */
	public void increaseNumberOfAppears(){
		this.numberOfAppears++;
	}
	
	/**
	 * @return ID list for the keyword
	 */
	public Set<Feed> getIds() throws NullPointerException{
		return ids;
	}
	
	public void setIds(final Set<Feed> ids){
		this.ids = ids;
	}
	
	/**
	 * Add a new feed's id which contains this keyword
	 * @param id
	 * @param keyword
	 */
	public void addID(final Feed ID){
			ids.add(ID);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Returns the feed ids
	 */
	public List<String> getFeedsIds(){
		List<String> feedIds = null;
		if(this.ids.size()>0){
			feedIds = new ArrayList<String>();
			for(int i=0;i<this.ids.size();i++){
				feedIds.add(((Feed) this.ids.toArray()[i]).getId_());
			}
		}
		return feedIds;
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Keyword)){
			return false;
		}
		Keyword key = (Keyword)o;
		if(this.value.trim().toLowerCase().equals(key.getValue().trim().toLowerCase())){
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return value.hashCode();
	}
}
