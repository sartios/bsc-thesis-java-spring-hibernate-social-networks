package com.sones.facebook.JsonHandler.Factory;

import org.apache.commons.beanutils.DynaBean;

import com.sones.facebook.tokenmanager.model.FacebookAccount;

public interface IFacebookAccountFactory 
{
	public FacebookAccount GetAccount(DynaBean beanObject);
}
