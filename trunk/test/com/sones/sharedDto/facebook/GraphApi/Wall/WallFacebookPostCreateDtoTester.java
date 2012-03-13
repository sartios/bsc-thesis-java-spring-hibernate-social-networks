package com.sones.sharedDto.facebook.GraphApi.Wall;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.feed.comment.Comment;

import static org.junit.Assert.*;

public class WallFacebookPostCreateDtoTester 
{
	private ApplicationContext context;
	private DozerBeanMapper mapper;
	
	public WallFacebookPostCreateDtoTester()
	{
		context = new ClassPathXmlApplicationContext("spring-wall-sharedDto-nu.xml");
		List<String> mappingFiles = new  ArrayList<String>();
		mappingFiles.add("Dozer/facebook/GraphApi/Wall/CreateWallMapper.xml");
		mapper = new DozerBeanMapper(mappingFiles);
	}
	
	@Test
	public void TestUser()
	{
		WallFacebookPostCreateDto postDto = (WallFacebookPostCreateDto) context.getBean("postDto");
		FacebookPost postModel = mapper.map(postDto, FacebookPost.class);
		assertEquals(postDto.getUser().getId().getId(), postModel.getUser().getId());
		assertEquals(postDto.getUser().getUsername(), postModel.getUser().getUsername());
	}
	
	@Test
	public	void	TestComments()
	{
		WallFacebookPostCreateDto postDto = (WallFacebookPostCreateDto) context.getBean("postDto");
		FacebookPost postModel = mapper.map(postDto, FacebookPost.class);
		WallCommentCreateDto commentFromDto = postDto.getComments().iterator().next();
		Comment commentFromModel = postModel.getComments().iterator().next();
		assertEquals(commentFromDto.getId().getId(), commentFromModel.getId());
		assertEquals(commentFromDto.getMessage(), commentFromModel.getMessage());
		assertEquals(commentFromDto.getUser().getId().getId(), commentFromModel.getUser().getId());
		assertEquals(commentFromDto.getUser().getUsername(), commentFromModel.getUser().getUsername());
		
	}
}
