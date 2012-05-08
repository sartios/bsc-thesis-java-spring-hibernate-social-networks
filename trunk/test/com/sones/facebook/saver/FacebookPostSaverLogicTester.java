package com.sones.facebook.saver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.saver.FacebookPostSaverLogic;
import com.sones.facebook.saver.IFacebookPostSaverLogic;
import com.sones.sharedDto.exceptions.MapErrorException;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallFacebookPostCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallSourceCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallSourceFacebookPostCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallSourceFacebookPostIdDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallStatusMessageCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallUserCreateDto;
import com.sones.sharedDto.facebook.feed.FacebookPostIdDto;
import com.sones.sharedDto.facebook.source.UserIdDto;

public class FacebookPostSaverLogicTester
{
	private	IFacebookPostSaverLogic saverLogic;
	private ApplicationContext context;
	private List<WallSourceFacebookPostCreateDto> sourcePostDtos;
	private WallSourceFacebookPostCreateDto sourcePostDto;
	private WallSourceFacebookPostIdDto id;
	private WallStatusMessageCreateDto post;
	private FacebookPostIdDto postId;
	private WallUserCreateDto user;
	private WallSourceCreateDto source;
	
	public FacebookPostSaverLogicTester()
	{
		context = new ClassPathXmlApplicationContext("FacebookDownloader/spring-facebook-saver-logic.xml");
		saverLogic = (IFacebookPostSaverLogic) context.getBean("saverLogic");
	}
	
	@Before
	public void setup()
	{
		sourcePostDtos = new ArrayList<WallSourceFacebookPostCreateDto>();
		
		sourcePostDto = new WallSourceFacebookPostCreateDto();
		
		id = new WallSourceFacebookPostIdDto();
		
		post = new WallStatusMessageCreateDto();
		postId = new FacebookPostIdDto();
		postId.setId("facebook post id");
		post.setId(postId);
		user = new WallUserCreateDto();
		UserIdDto userId = new UserIdDto();
		userId.setId("user id");
		user.setId(userId );
		post.setUser(user);
		post.setType("StatusMessage");
		
		source = new WallSourceCreateDto();
		source.setId("source id");
		source.setType("test type");
		
		id.setPost(post);
		id.setSource(source);
		sourcePostDto.setId(id );
	}
	
	@Test
	public void shouldWorkCorrectlyWithValidValues()
	{
		sourcePostDtos.add(sourcePostDto);	
		saverLogic.saveWallPosts(sourcePostDtos);
	}
	
	@Test(expected = MapErrorException.class)
	public void shouldThrowOnNullSource()
	{
		id.setSource(null);
		sourcePostDto.setId(id);
		sourcePostDtos.add(sourcePostDto);	
		saverLogic.saveWallPosts(sourcePostDtos);
	}
	
	@Test(expected = MapErrorException.class)
	public void shouldThrowOnNullPost()
	{
		id.setPost(null);
		sourcePostDto.setId(id);
		sourcePostDtos.add(sourcePostDto);	
		saverLogic.saveWallPosts(sourcePostDtos);
	}
	
	@Test
	public void shouldCatchTheException()
	{
		try
		{
			id.setPost(null);
			sourcePostDto.setId(id);
			sourcePostDtos.add(sourcePostDto);	
			saverLogic.saveWallPosts(sourcePostDtos);
		}
		catch (MapErrorException e) {
			// TODO: handle exception
		}
	}
}