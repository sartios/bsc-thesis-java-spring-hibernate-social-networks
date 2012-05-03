package com.sones.facebook.keyword.result.logic;

import java.util.List;

import com.sones.sharedDto.facebook.result.FacebookResultViewDto;
import com.sones.usermanager.model.ApplicationUser;

public interface IFaceboookPostKeywordResultService 
{
	public List<FacebookResultViewDto> getFacebookKeywordResults(ApplicationUser appUser);
}
