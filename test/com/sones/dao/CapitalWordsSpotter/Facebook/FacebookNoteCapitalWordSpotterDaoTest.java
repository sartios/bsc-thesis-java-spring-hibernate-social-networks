package com.sones.dao.CapitalWordsSpotter.Facebook;

import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class FacebookNoteCapitalWordSpotterDaoTest {
	@Test
	public	void	GetFeedsBetweenDatesTest()
	{
		FacebookNoteCapitalWordSpotterDao	dao	=	new	FacebookNoteCapitalWordSpotterDao();
		List feeds	=	dao.GetFeedsBetweenDates("Mon Nov 14 22:46:00 EET 2011", Calendar.getInstance().getTime().toString());
		Assert.assertNotNull(feeds);
	}
}
