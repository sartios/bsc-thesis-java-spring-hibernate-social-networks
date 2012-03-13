package com.sones.sharedDto.facebook.GraphApi.Wall;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.model.feed.SourceFacebookPostId;

public class WallSourceFacebookPostIdDtoTester 
{
	private ApplicationContext context;
	private DozerBeanMapper mapper;
	
	public WallSourceFacebookPostIdDtoTester()
	{
		context = new ClassPathXmlApplicationContext("spring-wall-sharedDto-nu.xml");
		List<String> mappingFiles = new  ArrayList<String>();
		mappingFiles.add("Dozer/facebook/GraphApi/Wall/CreateWallMapper.xml");
		mapper = new DozerBeanMapper(mappingFiles);
	}
	
	@Test
	public	void	TestSourceFacebookPost()
	{
		WallSourceFacebookPostIdDto id = (WallSourceFacebookPostIdDto) context.getBean("sourceFacebookPostId");	
		SourceFacebookPostId idModel = mapper.map(id, SourceFacebookPostId.class);
		Assert.assertEquals(id.getPost().getId().getId(), idModel.getPost().getId());
		Assert.assertEquals(id.getSource().getId(), idModel.getSource().getId());
	}
}
