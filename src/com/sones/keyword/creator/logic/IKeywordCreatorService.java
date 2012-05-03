package com.sones.keyword.creator.logic;

import com.sones.facebook.keywordSearcher.model.Keyword;

public interface IKeywordCreatorService 
{
	public void createKeyword(String keywordValue);
	
	public Iterable<Keyword> getKeywords();
}
