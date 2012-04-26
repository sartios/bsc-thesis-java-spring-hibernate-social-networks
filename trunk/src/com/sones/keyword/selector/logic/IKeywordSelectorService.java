package com.sones.keyword.selector.logic;

import com.sones.facebook.keywordSearcher.model.Keyword;
import com.sones.usermanager.model.ApplicationUser;

public interface IKeywordSelectorService 
{
	public void selectKeyword(ApplicationUser appUser, Keyword keyword);
}
