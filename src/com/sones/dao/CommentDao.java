package com.sones.dao;

import com.exceptions.DataAccessLayerException;
import com.sones.businessLogic.Facebook.Comment;
import com.sones.businessLogic.Facebook.CommentList;

public class CommentDao extends AbstractDao{
	
	public CommentDao(){
		super();
	}
	
	public boolean saveOrUpdate(Comment comment) throws DataAccessLayerException{
		boolean isSaved=false;
		try{
			isSaved=super.saveOrUpdate(comment);
		}
		catch(DataAccessLayerException ex){
			isSaved=false;
		}
		return isSaved;
	}
	
	public void delete(Comment comment) throws DataAccessLayerException{
		super.delete(comment);
	}
	
	public Comment find(String id){
		return (Comment) find(Comment.class,id);
	}
	
	public boolean saveCommentList(final CommentList comments){
		boolean operationCompleted = true;
		if(null!=comments){
			try{
				for(int i=0;i<comments.getSize();i++){
					if((this.saveOrUpdate(comments.getComment(i)))&&operationCompleted){
						operationCompleted = true;
					}
					else{
						operationCompleted=false;
					}
				}
			}
			catch (Exception e) {
				operationCompleted = false;
			}
		}
		else{
			operationCompleted=false;
		}
		return operationCompleted;
	}
	
	public boolean deleteCommentList(final CommentList comments){
		boolean operationCompleted = false;
		if(null!=comments){
			try{
				for(int i=0;i<comments.getSize();i++){
					this.delete(comments.getComment(i));
				}
				operationCompleted = true;
			}
			catch (Exception e) {
				operationCompleted = false;
			}
		}
		return operationCompleted;
	}
}
