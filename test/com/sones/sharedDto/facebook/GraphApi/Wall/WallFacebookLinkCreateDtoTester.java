package com.sones.sharedDto.facebook.GraphApi.Wall;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.model.feed.Link;
import com.sones.facebook.model.feed.comment.Comment;

public class WallFacebookLinkCreateDtoTester 
{
	private ApplicationContext context;
	private DozerBeanMapper mapper;
	
	public WallFacebookLinkCreateDtoTester()
	{
		context = new ClassPathXmlApplicationContext("spring-wall-sharedDto-nu.xml");
		List<String> mappingFiles = new  ArrayList<String>();
		mappingFiles.add("Dozer/facebook/GraphApi/Wall/CreateWallMapper.xml");
		mapper = new DozerBeanMapper(mappingFiles);
	}
	
	@Test
	public	void	TestLink()
	{
		WallLinkCreateDto linkDto = (WallLinkCreateDto) context.getBean("linkDto");
		Link linkModel = mapper.map(linkDto, Link.class);
		Assert.assertEquals("Id mapping has error",linkDto.getId().getId(), linkModel.getId());
		Assert.assertEquals("Link mapping has error",linkDto.getLink(), linkModel.getLink());
		Assert.assertEquals("Description mapping has error",linkDto.getDescription(), linkModel.getDescription());
		Assert.assertEquals("Icon mapping has error",linkDto.getIcon(), linkModel.getIcon());
		Assert.assertEquals("Message mapping has error",linkDto.getMessage(), linkModel.getMessage());
		Assert.assertEquals("Name mapping has error",linkDto.getName(), linkModel.getName());
		Assert.assertEquals("Picture mapping has error",linkDto.getPicture(), linkModel.getPicture());
		Assert.assertEquals("User id mapping has error",linkDto.getUser().getId().getId(), linkModel.getUser().getId());
		Assert.assertEquals("Usernames mapping has error",linkDto.getUser().getUsername(), linkModel.getUser().getUsername());
	}
	
	@Test
	public	void	TestLinkComments()
	{
		WallLinkCreateDto linkDto = (WallLinkCreateDto) context.getBean("linkDto");
		Link linkModel = mapper.map(linkDto, Link.class);
		WallCommentCreateDto	commentDto = linkDto.getComments().iterator().next();
		Comment commentModel = linkModel.getComments().iterator().next();
		Assert.assertEquals("Comment id mapping has error",commentDto.getId().getId(), commentModel.getId());
		Assert.assertEquals("Comment message mapping has error",commentDto.getMessage(), commentModel.getMessage());
		Assert.assertEquals("Comment user id mapping has error",commentDto.getUser().getId().getId(), commentModel.getUser().getId());
		Assert.assertEquals("Comment user name mapping has error",commentDto.getUser().getUsername(), commentModel.getUser().getUsername());
	}
}
