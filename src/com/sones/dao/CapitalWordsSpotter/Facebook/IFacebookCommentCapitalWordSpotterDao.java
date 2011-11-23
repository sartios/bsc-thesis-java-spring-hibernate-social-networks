package com.sones.dao.CapitalWordsSpotter.Facebook;

import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.ICapitalWordsSearchableComments;

/**
 * Provides methods for handling comments that haven't been searched for capital words.
 * 
 * @author Savramis	Sartios
 *
 */
public interface IFacebookCommentCapitalWordSpotterDao
{
	public ICapitalWordsSearchableComments GetCommentsBetweenDates( String startDate, String endDate);
}
