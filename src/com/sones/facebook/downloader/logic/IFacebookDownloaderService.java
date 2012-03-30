package com.sones.facebook.downloader.logic;


import com.sones.userManager.model.ApplicationUser;

/**
 * Provides methods for downloading facebook posts.
 * @author sartios.sones@gmail.com.
 *
 */
public	interface	IFacebookDownloaderService
{
	public	void	DownloadWallPosts( ApplicationUser appUser );
}
