package com.sones.businessLogic.Searcher;

public interface ISearchingTimer 
{
	public	void	Start();
	public	void	Stop();
	public	void	SetTime( final String time );
	public	void	SetSearcher( final ISearcher searcher );
}
