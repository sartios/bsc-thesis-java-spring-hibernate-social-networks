package com.sones.facebook.post.saver;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import testingUtilities.FeedReader;

import com.sones.facebook.JsonHandler.FacebookJsonHandler;
import com.sones.facebook.JsonHandler.IFacebookJsonHandler;
import com.sones.facebook.saver.FacebookPostSaverLogic;
import com.sones.facebook.saver.IFacebookPostSaverLogic;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallFacebookPostCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallSourceCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallSourceFacebookPostCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallSourceFacebookPostIdDto;

public class FacebookPostSaverLogicTester 
{
	private	IFacebookPostSaverLogic saverLogic;
	private	IFacebookJsonHandler jsonHandler;
	
	public FacebookPostSaverLogicTester()
	{
		saverLogic = new FacebookPostSaverLogic();
		jsonHandler = new FacebookJsonHandler();
	}
	
	@Test
	public void TestSaveWallPosts()
	{
		FeedReader feedReader = new FeedReader();
		Iterable<WallFacebookPostCreateDto> postsDto = jsonHandler.GetWallPosts(feedReader.getFacebookWall());
		Iterator<WallFacebookPostCreateDto> iterator = postsDto.iterator();
		Set<WallSourceFacebookPostCreateDto> sourcePostsDto = new HashSet<WallSourceFacebookPostCreateDto>();
		WallSourceCreateDto sourceDto = new WallSourceCreateDto();
		sourceDto.setId("1");
		sourceDto.setType("User");
		WallFacebookPostCreateDto postDto = iterator.next();
		for(;iterator.hasNext();postDto=iterator.next())
		{
			WallSourceFacebookPostCreateDto sourceFacebookPost = new WallSourceFacebookPostCreateDto();
			WallSourceFacebookPostIdDto sourceFacebookPostId = new WallSourceFacebookPostIdDto();
			sourceFacebookPostId.setPost(postDto);
			sourceFacebookPostId.setSource(sourceDto);
			sourceFacebookPost.setId(sourceFacebookPostId);
			sourcePostsDto.add(sourceFacebookPost);
		}
		saverLogic.saveWallPosts(sourcePostsDto);
	}
}
