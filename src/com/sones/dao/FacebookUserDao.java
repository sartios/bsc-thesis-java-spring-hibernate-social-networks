package com.sones.dao;

import com.exceptions.DataAccessLayerException;
import com.sones.businessLogic.Facebook.FacebookUser;
import com.sones.businessLogic.Facebook.Feed;

/**
 * This class saves the facebook user it's responsible for persisting facebook
 * user into database.
 * @author Sartios
 *
 */
public class FacebookUserDao extends AbstractDao{
	
	public FacebookUserDao(){
		super();
	}
	
	/**
	 * It saves or updates the facebook user.
	 * @param facebookUser to be persisted
	 * @return true if the saving was completed
	 * @throws DataAccessLayerException in this version of the method,
	 * we catch the exception internally and we just print "Application user id isn't valid".
	 * In the future we should log this message.
	 */
	public boolean saveOrUpdate(FacebookUser facebookUser) throws DataAccessLayerException{
		
		boolean isSaved=false;
		if(null!=facebookUser){
			try{
				super.saveOrUpdate(facebookUser);
				isSaved = true;
			}
			catch (DataAccessLayerException ex) {
				System.out.println("Application user id isn't valid");
				isSaved=false;
			}
		}
		return isSaved;
	}
	
	/**
	 * Deletes the given user
	 * @param facebookUser
	 * @return true if the deletion was done
	 * @throws DataAccessLayerException in this version of the method, we catch
	 * the exception internally and we print the message "User doesn't exist".
	 * In the future we should log this message.
	 */
	public boolean delete(FacebookUser facebookUser) throws DataAccessLayerException{
		boolean isDeleted=false;
		if(null!=facebookUser){
			try{
				isDeleted=super.delete(facebookUser);
				isDeleted=true;
			}
			catch (DataAccessLayerException e) {
				System.out.print("User doesn't exist");
				isDeleted=false;
			}
		}
		return isDeleted;
	}
	
	/**
	 * We return the facebook user who has the given facebook ID
	 * @param id from facebook user
	 * @return facebook user if exist, or null
	 */
	public FacebookUser find(String id){
		FacebookUser facebookUser = null;
		if(null!=id){
			try{
				facebookUser=(FacebookUser) find(FacebookUser.class,id);
			}
			catch(NullPointerException ex){
			}
		}
		return facebookUser;
	}
}
