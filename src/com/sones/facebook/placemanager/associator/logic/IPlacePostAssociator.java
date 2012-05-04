package com.sones.facebook.placemanager.associator.logic;

import com.sones.usermanager.model.ApplicationUser;

/**
 * Provides methods for associating the facebook posts with the place and its criteria.
 * @author sartios.sones@gmail.com.
 *
 */
public interface IPlacePostAssociator 
{
	/**
	 * Associates the posts of user with the places from where they were downloaded.
	 * 
	 * @param appUser the application user
	 * @throws IllegalArgumentException if the application user is null.
	 * @throws NoSuchSourceType if there is not a place source type.
	 */
	public void AssociatePlaceWithPosts(ApplicationUser appUser);
}
