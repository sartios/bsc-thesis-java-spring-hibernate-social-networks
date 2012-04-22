package com.sones.facebook.downloader.dao;

import java.util.Date;

import com.sones.dao.IGenericDao;
import com.sones.facebook.downloader.model.FacebookDownload;
import com.sones.usermanager.model.ApplicationUser;

/**
 * Provides methods for accessing {@link FacebookDownload} model.
 * @author sartios.sones@gmail.com.
 *
 */
public interface IFacebookDownloadDao	extends	IGenericDao<FacebookDownload, String>
{

	/**
	 * Returns the last download of the specified application user
	 * @param appUser the application user
	 * @return facebook download
	 * @throws IllegalArgumentException if application user is null.
	 */
	public FacebookDownload GetLastAppUserDownload(ApplicationUser appUser);
	
	/**
	 * Returns the facebook downloads of the <code>application user<code> after the specified <code>date</code>.
	 * @param appUser the application user
	 * @param date the after date
	 * @return facebook downloads
	 * @throws IllegalArgumentException if application user or date is null.
	 */
	public Iterable<FacebookDownload> GetByAppUserAfterDate(ApplicationUser appUser,Date date);
}
