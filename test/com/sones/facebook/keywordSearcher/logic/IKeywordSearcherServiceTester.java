package com.sones.facebook.keywordSearcher.logic;

public interface IKeywordSearcherServiceTester 
{
	public	void	testSearchForKeywordsWhenEveryPostContainsTheKeyword();
	
	public	void	testSearchForKeywordsWhenThereAreSomePostsThatContainTheKeywords();
	
	public	void	testSearchForKeywordsWhenThereAreNotPostsThatContainTheKeywords();
}
