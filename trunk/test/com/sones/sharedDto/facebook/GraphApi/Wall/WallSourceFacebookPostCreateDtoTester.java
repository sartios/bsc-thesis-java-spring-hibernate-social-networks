package com.sones.sharedDto.facebook.GraphApi.Wall;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.model.feed.SourceFacebookPost;

public class WallSourceFacebookPostCreateDtoTester 
{
	private ApplicationContext context;
	private DozerBeanMapper mapper;
	
	public WallSourceFacebookPostCreateDtoTester()
	{
		context = new ClassPathXmlApplicationContext("spring-wall-sharedDto-nu.xml");
		List<String> mappingFiles = new  ArrayList<String>();
		mappingFiles.add("Dozer/facebook/GraphApi/Wall/CreateWallMapper.xml");
		mapper = new DozerBeanMapper(mappingFiles);
	}
	
	@Test
	public	void	TestSourceFacebookPost()
	{
		WallSourceFacebookPostCreateDto dto = (WallSourceFacebookPostCreateDto) context.getBean("sourceFacebookPostDto");
		SourceFacebookPost model = mapper.map(dto, SourceFacebookPost.class);
		
		Assert.assertEquals(dto.getId().getPost().getId().getId(), model.getId().getPost().getId());
		Assert.assertEquals(dto.getId().getSource().getId(), model.getId().getSource().getId());
	}
}
