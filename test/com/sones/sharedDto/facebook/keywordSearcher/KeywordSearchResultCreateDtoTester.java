package com.sones.sharedDto.facebook.keywordSearcher;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.Test;

import com.sones.facebook.keywordSearcher.model.FacebookPostKeywordResult;
import com.sones.sharedDto.facebook.feed.FacebookPostIdDto;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.FacebookPostSearchDto;

public class KeywordSearchResultCreateDtoTester 
{
	private	DozerBeanMapper	mapper;
	
	public	KeywordSearchResultCreateDtoTester()
	{
		List<String> mappingFiles = new  ArrayList<String>();
		mappingFiles.add("Dozer/facebook/keywordSearcher/SearchFeedMapper.xml");
		mapper = new DozerBeanMapper(mappingFiles);

	}
	
	@Test
	public	void	mapKeywordSearchResultCreateDto()
	{
		KeywordSearchResultCreateDto	resultDto	=	new	KeywordSearchResultCreateDto();
		
		KeywordSearchDto	keywordDto	=	new	KeywordSearchDto();
		keywordDto.setId("1");
		keywordDto.setValue("value");
		resultDto.setKeyword(keywordDto);
		
		FacebookPostSearchDto	postDto	=	new	FacebookPostSearchDto();
		FacebookPostIdDto	id	=	new	FacebookPostIdDto();
		id.setId("1");
		postDto.setId(id);
		resultDto.setPost(postDto);
		
		FacebookPostKeywordResult	result	=	new	FacebookPostKeywordResult();
		mapper.map( resultDto , result );
		
		assertEquals( resultDto.getKeyword().getId() , result.getKeyword().getId() );
		assertEquals( resultDto.getKeyword().getValue() , result.getKeyword().getValue() );
		assertEquals( resultDto.getPost().getId().getId() , result.getPost().getId() );
	}
}
