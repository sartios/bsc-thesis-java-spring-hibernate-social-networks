package com.sones.businessLogic.Searcher.Results;

public interface IResult 
{
	public	String	GetKeyword();
	public	void	SetKeyword( final String keyword );
	
	public	String	GetFeedID();
	public	void	SetFeedID( final String feedID );
}
