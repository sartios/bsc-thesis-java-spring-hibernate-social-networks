package com.sones.facebook.keywordSearcher.dao;

import com.sones.dao.IGenericDao;
import com.sones.facebook.keywordSearcher.model.KeywordSearchOption;
import com.sones.usermanager.model.ApplicationUser;

public interface IKeywordSearchOptionDao extends IGenericDao<KeywordSearchOption, String>
{
	KeywordSearchOption getByApplicationUser(ApplicationUser appUser);
}
