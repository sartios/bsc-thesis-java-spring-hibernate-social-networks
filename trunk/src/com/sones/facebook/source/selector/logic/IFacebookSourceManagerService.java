package com.sones.facebook.source.selector.logic;

import com.sones.sharedDto.facebook.source.selector.SourceCreateDto;
import com.sones.sharedDto.facebook.source.selector.SourceViewDto;

public interface IFacebookSourceManagerService 
{
	public Iterable<SourceViewDto> getSelectedSources(String applicationUserId);
	
	public void deleteSelectedSources(String applicationUserId, Iterable<SourceCreateDto> sourcesDto);
}
