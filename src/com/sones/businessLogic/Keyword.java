package com.sones.businessLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Keyword {

	/**
	 * The keyword's value
	 */
	private String value_;
	
	/**
	 * Keyword's times of appear
	 */
	private int numberOfAppears;
	
	/**
	 * The threshold of keyword's appearing
	 */
	private int threshold_;
	
	/**
	 * The list of IDs that contain this keyword
	 */
	private List<String> ids_;
	
	public Keyword(){
		this.value_ = new String();
		this.numberOfAppears = 0;
		this.threshold_ = -1;
		this.ids_ = new ArrayList<String>();
	}
	
	/**
	 * Constructor with arguments
	 * @param value
	 */
	public Keyword(final String value){
		if(null!=value){
			this.value_ = value;
		}
		this.numberOfAppears = 0;
		this.threshold_ = -1;
		this.ids_ = new ArrayList<String>();
	}
	
	/**
	 * Constructor with arguments
	 * @param value
	 */
	public Keyword(final String value,final int threshold){
		if(null!=value){
			this.value_ = value;
		}
		if(threshold<0){
			this.threshold_ = -1;
		}
		this.numberOfAppears = 0;
		this.threshold_ = threshold;
		this.ids_ = new ArrayList<String>();
	}
	
	/**
	 * @return value of the keyword
	 */
	public String getValue(){
		return this.value_.toString();
	}
	
	public int getNumberOfAppears(){
		return this.numberOfAppears;
	}
	
	/**
	 * @return true if the threshold is equal of smaller than number of appears
	 */
	public boolean canBeNotifiable(){
		return threshold_ <= numberOfAppears;
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
	public List<String> getIDs() throws NullPointerException{
		return Collections.unmodifiableList(ids_);
	}
	
	/**
	 * Add a new feed's id which contains this keyword
	 * @param id
	 * @param keyword
	 */
	public void addID(final String ID){
		if(idDoesntExist(ids_,ID)){
			ids_.add(ID);
		}
	}
	
	/**
	 * @param feed's ID
	 * @return true if the id doesn't exist into the keyword's ID list
	 */
	private boolean idDoesntExist(final List<String> ids,final String ID){
		if((null!=ids)&&(null!=ID)){
			for(int i=0;i<ids.size();i++){
				if(ids.get(i)==ID){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Keyword)){
			return false;
		}
		Keyword key = (Keyword)o;
		if(this.value_.trim().toLowerCase().equals(key.getValue().trim().toLowerCase())){
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return value_.hashCode();
	}
}
