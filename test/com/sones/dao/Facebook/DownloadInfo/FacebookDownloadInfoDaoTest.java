package com.sones.dao.Facebook.DownloadInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.sones.businessLogic.Facebook.FacebookDownloadInfo;
import com.sones.businessLogic.Facebook.IFacebookDownloadInfo;

public class FacebookDownloadInfoDaoTest 
{
	@Test
	public	void	SaveDownloadingInfo()
	{
		IFacebookDownloadInfo	info	=	new	FacebookDownloadInfo();
		info.SetDate(Calendar.getInstance().getTime().toString());
		info.SetUserID("1");
		List<String>	feeds	=	new	ArrayList<String>();
		List<String>	comments	=	new	ArrayList<String>();
		
		feeds.add("545564400_10150337641464401");
		feeds.add("545564400_10150337654104401");
		feeds.add("545564400_10150337665334401");
		feeds.add("545564400_10150338900184401");

		comments.add("545564400_10150348886274401_19039350");
		comments.add("545564400_10150348886274401_19039971");
		comments.add("545564400_10150350046454401_19056945");
		comments.add("545564400_10150350046454401_19057898");
		
		info.AddComments(comments);
		info.AddFeedIDs(feeds);
		
		IFacebookDownloadInfoDao	dao	=	new	FacebookDownloadInfoDao();
		dao.SaveDownloadInfo(info);
	}
}
