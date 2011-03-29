package com.sones.businessLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activity.InvalidActivityException;

public class ResultList {

	/**
	 * The Map that keeps the results of searching into feeds.
	 * The structure is keyword => List<ID>
	 */
	private Map<String,List<String>> results_;
	
	/**
	 * We can't create an empty result list because we need the 
	 * keywords so we can initialize it.
	 * @throws InvalidActivityException 
	 */
	public ResultList() throws InvalidActivityException{
		throw new InvalidActivityException("We need the keywords");
	}
	
	/**
	 * Constructor with the keywords
	 * @param keywords
	 */
	public ResultList(final List<String> keywords){
		results_ = new HashMap<String, List<String>>();
		for(int i=0;i<keywords.size();i++){
			results_.put(keywords.get(i), new ArrayList<String>());
		}
	}
	
	/**
	 * @param keyword
	 * @throws NullPointerException if the keyword doesn't exist into the list
	 * @return ID list for the keyword
	 */
	public List<String> getIDs(final String keyword) throws NullPointerException{
		if(null!=keyword){
				return Collections.unmodifiableList(results_.get(keyword));
		}
		return null;
	}
	
	/**
	 * Adds an ID to a keyword list
	 * @param id
	 * @param keyword
	 */
	public void addID(final String keyword,final String ID){
		List<String> ids = this.results_.get(keyword);
		if(idDoesntExist(ids,ID)){
			ids.add(ID);
		}
	}
	
	/**
	 * @param keyword's id list
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
}
