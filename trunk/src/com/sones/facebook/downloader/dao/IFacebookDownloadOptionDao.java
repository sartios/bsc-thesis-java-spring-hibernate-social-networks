package com.sones.facebook.downloader.dao;

import com.sones.dao.IGenericDao;
import com.sones.facebook.downloader.model.FacebookDownloadOption;
import com.sones.facebook.tokenmanager.model.FacebookAccount;

public interface IFacebookDownloadOptionDao extends IGenericDao<FacebookDownloadOption, String>
{

	FacebookDownloadOption getByAccount(FacebookAccount account);

}
