package com.sones.collections;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCollection {

	/**
	 * 
	 */
	private int index_;
	
	/**
	 * 
	 */
	private List<Object> objects_;
	
	/**
	 * 
	 */
	private final int defaultIndexValue_=-1;
	
	/**
	 * 
	 */
	public AbstractCollection(){
		objects_ = new ArrayList<Object>();
		index_=defaultIndexValue_;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean hasNext(){
		if(this.isEmpty()==false){
			if(index_==objects_.size()-1){
				return false;
			}
			else if(index_<objects_.size()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 */
	public void reset(){
		index_=defaultIndexValue_;
	}
	
	/**
	 * 
	 */
	public void increaseIndex(){
		this.index_++;
	}
	
	/**
	 * 
	 * @return
	 */
	public Object getNext(){
		if(this.hasNext()){
			index_++;
			return objects_.get(index_);
		}
		return null;
	}
	
	/**
	 * 
	 */
	public void addObject(final Object object){
		if(objectIsNotNull(object)){
			if(objectExists(object)==false){
				this.objects_.add(object);
			}
		}
	}
	
	/**
	 * 
	 */
	private boolean objectIsNotNull(final Object object){
		if(null!=object){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 */
	private boolean objectExists(final Object object){
		if(this.objects_.contains(object)){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 */
	public boolean isEmpty(){
		return this.objects_.isEmpty();
	}
	
	/**
	 * 
	 */
	public List getAll(){
		return this.objects_;
	}
}
