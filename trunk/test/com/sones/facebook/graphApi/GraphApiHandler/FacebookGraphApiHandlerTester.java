package com.sones.facebook.graphApi.GraphApiHandler;

import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

import com.sones.sharedDto.facebook.GraphApi.Wall.WallFacebookPostCreateDto;

public class FacebookGraphApiHandlerTester 
{
	private	ApplicationContext context;
	private	IFacebookGraphApiHandler handler;
	private	String	sourceId;
	private	String	token;
	
	public FacebookGraphApiHandlerTester()
	{
		context = new ClassPathXmlApplicationContext("spring-facebookGraphApi.xml");
		handler = (FacebookGraphApiHandler) context.getBean("graphApi");
		sourceId = "100000866964787";
		token = "access_token=AAAAAAITEghMBACz1DuIOk2aTEbNlLO63snOV3J4NEe9nP2xPSj2U3LQK0GHq7OBCJTciDSTIkseUagRSUnCFaGXhK9fJOKRL9BieOFUubkRd9UUA";
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void TestGetWallPostsNullSource()
	{
		handler.GetWallPosts(null, "a token");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void TestGetWallPostsEmptySource()
	{
		handler.GetWallPosts("", "a token");
	}
	@Test(expected=IllegalArgumentException.class)
	public void TestGetWallPostsNullToken()
	{
		handler.GetWallPosts("a source", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void TestGetWallPostsEmptyToken()
	{
		handler.GetWallPosts("a source", "");
	}
	
	@Test
	public void TestGetWallPostsSize()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(sourceId, token);
		assertEquals("Error @ post number",25, ((Set<WallFacebookPostCreateDto>)posts).size());
	}
}
