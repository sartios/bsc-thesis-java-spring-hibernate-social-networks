package com.sones.facebook.downloader.dao;

import java.util.Date;

import com.sones.dao.IGenericDao;
import com.sones.facebook.downloader.model.FacebookPostDownload;
import com.sones.facebook.downloader.model.FacebookPostDownloadId;
import com.sones.usermanager.model.ApplicationUser;

/**
 * Provides methods for accessing {@link FacebookPostDownload} model.
 * @author sartios.sones@gmail.com.
 *
 */
public interface IFacebookPostDownloadDao	extends	IGenericDao<FacebookPostDownload, FacebookPostDownloadId>
{
	/**
	 * Returns the facebook posts that were downloaded after the specified date and belongs to application user.
	 * @param date - The date of the download
	 * @param appUser - The application user who download the posts.
	 * @return Collection of {@link FacebookPostDownload} objects.
	 * @throws IllegalArgumentException when date or application user is null.
	 */
	public	Iterable<FacebookPostDownload>	getFacebookPostAfterDateByAppUser( Date date, ApplicationUser appUser );
}
