package com.sones.dao.CapitalWordsSpotter.Facebook;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.ICapitalWordsSearchableComments;

public class FacebookCommentsCapitalWordSpotterDaoTest 
{
	@Test
	public	void	GetFeedsBetweenDatesTest()
	{
		FacebookCommentsCapitalWordSpotterDao	dao	=	new	FacebookCommentsCapitalWordSpotterDao();
		ICapitalWordsSearchableComments comments	=	dao.GetCommentsBetweenDates("Mon Nov 14 22:46:00 EET 2011", Calendar.getInstance().getTime().toString());
		Assert.assertNotNull(comments);
	}
}
