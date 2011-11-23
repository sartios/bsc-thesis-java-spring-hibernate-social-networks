package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable;

import java.util.Iterator;

/**
 * Provides methods for handling a collection of {@link ICapitalWordsSearchableFeed}
 * 
 * @author Savramis	Sartios
 *
 */
public interface ICapitalWordsSearchableFeeds 
{
	public	Iterator< ICapitalWordsSearchableFeed >	GetIterator();
}