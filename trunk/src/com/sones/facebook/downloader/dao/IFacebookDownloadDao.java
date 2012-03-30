package com.sones.facebook.downloader.dao;

import com.sones.dao.IGenericDao;
import com.sones.facebook.downloader.model.FacebookDownload;
import com.sones.userManager.model.ApplicationUser;

/**
 * Provides methods for accessing {@link FacebookDownload} model.
 * @author sartios.sones@gmail.com.
 *
 */
public interface IFacebookDownloadDao	extends	IGenericDao<FacebookDownload, String>
{

	FacebookDownload GetLastAppUserDownload(ApplicationUser appUser);

}
