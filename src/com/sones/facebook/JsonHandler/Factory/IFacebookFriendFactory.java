package com.sones.facebook.JsonHandler.Factory;

import org.apache.commons.beanutils.DynaBean;

import com.sones.facebook.downloader.model.FacebookFriend;

public interface IFacebookFriendFactory {

	public FacebookFriend GetFriend(DynaBean beanObject);

}
