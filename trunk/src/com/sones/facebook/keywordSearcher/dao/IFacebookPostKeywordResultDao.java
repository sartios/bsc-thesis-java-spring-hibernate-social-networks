package com.sones.facebook.keywordSearcher.dao;

import com.sones.dao.IGenericDao;
import com.sones.facebook.keywordSearcher.model.FacebookPostKeywordResult;
import com.sones.userManager.model.ApplicationUser;

public interface IFacebookPostKeywordResultDao extends IGenericDao<FacebookPostKeywordResult, String>
{

	public Iterable<FacebookPostKeywordResult> GetByApplicationUser( ApplicationUser appUser );

}
