package com.sones.businessLogic.Downloader;

import java.util.Calendar;

public class DownloadingDateGenerator	implements	IDownloadingDateGenerator
{

	@Override
	public String GetDownloadingDate()
	{
		return	Calendar.getInstance().getTime().toString();
	}

}
