package com.sones.businessLogic.Searcher;

import java.util.Iterator;

public interface ISearchableFeeds 
{
	public	boolean	IsEmpty();
	public	Iterator< ISearchableFeed >	GetIterator();
	public	void	AddFeed( final ISearchableFeed feed );
	public	int	GetSize();
}
