package com.sones.businessLogic.Facebook.Source;

import java.util.ArrayList;
import java.util.List;

import com.sones.collections.AbstractCollection;

public class FacebookSourceList extends AbstractCollection implements IFacebookSourceCollection{

	/**
	 * Constructor with no arguments
	 */
	public FacebookSourceList(){
		super();
	}
	
	/**
	 * Add friend source into the list
	 * @param friend
	 */
	public void addSource(final ISource source){
		if(sourceIsNotNull(source)){
			super.addObject(source);
		}
	}
	
	/**
	 * Check if the source is null. If it's not returns true
	 * @param source
	 * @return true if the source is not null
	 */
	private boolean sourceIsNotNull(final ISource source){
		if(null!=source){
			return true;
		}
		return false;
	}
	
	/**
	 * We get the next source
	 * @return source
	 */
	@Override
	public ISource getNext(){
		return (ISource)super.getNext();
	}

	/**
	 * We get true if there are more sources in the list
	 */
	@Override
	public boolean hasNext() {
		return super.hasNext();
	}
	
	/**
	 * 
	 */
	@Override
	public void reset(){
		super.reset();
	}

}
