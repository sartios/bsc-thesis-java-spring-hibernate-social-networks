package com.sones.businessLogic.Facebook;

import java.util.HashSet;
import java.util.Set;

public class CommentList {

	private Set<Comment> comments_;
	
	public CommentList(){
		comments_=new HashSet<Comment>();
	}
	
	public boolean addComment(final Comment comment){
		if(null!=comment){
			return this.comments_.add(comment);
		}
		return false;
	}
	
	public boolean deleteComment(final Comment comment){
		if(null!=comment){
			return this.comments_.remove(comment);
		}
		return false;
	}
	
	public int getSize(){
		return this.comments_.size();
	}
	
	public Comment getComment(final int index){
		try{
			if(indexIsValid(index)){
				return (Comment) (this.comments_.toArray())[index];
			}
		}
		catch(ArrayIndexOutOfBoundsException ex){}
		return null;
	}
	
	private boolean indexIsValid(final int index){
		if((-1<index)&&(index<=this.comments_.size())){
			return true;
		}
		return false;
	}
	
	public void clear(){
		this.comments_.clear();
	}
	
	public boolean isEmpty(){
		return this.comments_.isEmpty();
	}
	
	/**
	 * Check if the keyword exists in comments. Doesn't matter if exists in the first
	 * or in the last comment. The only thing that is important now is to exists.
	 *
	 * @param keyword
	 * @return true if keyword exits
	 */
	public boolean contain(final String keyword){
		if(null!=keyword){
			for(int i=0;i<this.getSize();i++){
				if(this.getComment(i).find(keyword)){
					return true;
				}
			}
		}
		return false;
	}
}
