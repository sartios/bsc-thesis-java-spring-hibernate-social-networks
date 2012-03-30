package com.sones.facebook.saver.wall.feed;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.saver.wall.feed.FacebookWallPostSaver;
import com.sones.facebook.saver.wall.feed.IFacebookWallPostSaver;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallFacebookPostCreateDto;

public class FacebookWallPostSaverTester 
{
	private	IFacebookWallPostSaver wallSaver;
	private	ApplicationContext context;
	
	@Test
	public	void TestSavePost()
	{
		//context = new ClassPathXmlApplicationContext("spring-facebook-downloader-nu.xml");
		wallSaver = new FacebookWallPostSaver();
		//WallFacebookPostCreateDto post = (WallFacebookPostCreateDto)context.getBean("statusCreateDto");
		//wallSaver.SavePost(post);
	}
}
