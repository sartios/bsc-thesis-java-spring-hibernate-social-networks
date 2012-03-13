package com.sones.sharedDto.facebook.GraphApi.Wall;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.model.source.Source;

public class WallSourceCreateDtoTester 
{
	private ApplicationContext context;
	private DozerBeanMapper mapper;
	
	public WallSourceCreateDtoTester()
	{
		context = new ClassPathXmlApplicationContext("spring-wall-sharedDto-nu.xml");
		List<String> mappingFiles = new  ArrayList<String>();
		mappingFiles.add("Dozer/facebook/GraphApi/Wall/CreateWallMapper.xml");
		mapper = new DozerBeanMapper(mappingFiles);
	}
	
	@Test
	public	void	TestSource()
	{
		WallSourceCreateDto source = (WallSourceCreateDto) context.getBean("sourceDto");
		Source sourceModel = mapper.map(source, Source.class);
		Assert.assertEquals(source.getId(), sourceModel.getId());
		Assert.assertEquals(source.getType(), sourceModel.getType().getType());
	}
}
