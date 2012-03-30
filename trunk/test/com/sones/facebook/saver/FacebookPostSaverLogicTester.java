package com.sones.facebook.saver;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.saver.FacebookPostSaverLogic;
import com.sones.facebook.saver.IFacebookPostSaverLogic;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallSourceFacebookPostCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallStatusMessageCreateDto;

public class FacebookPostSaverLogicTester
{
	private	IFacebookPostSaverLogic saverLogic;
	private ApplicationContext context;
	
	public FacebookPostSaverLogicTester()
	{
		saverLogic = new FacebookPostSaverLogic();
	}
	
	@Test
	public void	TestSaveWallPosts()
	{
		context = new ClassPathXmlApplicationContext("spring-facebook-downloader-nu.xml");
		WallSourceFacebookPostCreateDto postDto = (WallSourceFacebookPostCreateDto)context.getBean("sourceStatusCreateDto");
		Set<WallSourceFacebookPostCreateDto> posts = new HashSet<WallSourceFacebookPostCreateDto>();
		System.out.println(((WallStatusMessageCreateDto)postDto.getId().getPost()).getMessage());
		posts.add(postDto);
		saverLogic.saveWallPosts(posts);
	}
}