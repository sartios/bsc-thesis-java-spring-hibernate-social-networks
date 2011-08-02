package com.sones.businessLogic.Searcher;

import com.sones.businessLogic.KeywordManager.KeywordList;

public interface ISearcher {

	public void search();
	public KeywordList getResults();
}
