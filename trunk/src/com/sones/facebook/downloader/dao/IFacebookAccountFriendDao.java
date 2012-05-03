package com.sones.facebook.downloader.dao;

import com.sones.dao.IGenericDao;
import com.sones.facebook.downloader.model.FacebookAccountFriend;
import com.sones.facebook.downloader.model.FacebookAccountFriendId;
import com.sones.facebook.tokenmanager.model.FacebookAccount;

public interface IFacebookAccountFriendDao extends IGenericDao<FacebookAccountFriend, FacebookAccountFriendId>
{

	public Iterable<FacebookAccountFriend> getByOwner(FacebookAccount owner);

}
