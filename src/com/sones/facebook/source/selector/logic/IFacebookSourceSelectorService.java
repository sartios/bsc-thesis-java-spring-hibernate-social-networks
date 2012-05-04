package com.sones.facebook.source.selector.logic;

import com.sones.sharedDto.facebook.source.selector.SourceCreateDto;

public interface IFacebookSourceSelectorService 
{
	public void saveSources(Iterable<SourceCreateDto> sourceCreateDtos);
}
