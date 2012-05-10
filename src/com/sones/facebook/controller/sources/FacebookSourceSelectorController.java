package com.sones.facebook.controller.sources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.downloader.logic.IFacebookFriendDownloaderService;
import com.sones.facebook.downloader.model.FacebookFriend;
import com.sones.facebook.source.selector.logic.IFacebookSourceSelectorService;
import com.sones.sharedDto.facebook.source.selector.SourceCreateDto;

public class FacebookSourceSelectorController 
{
	private IFacebookFriendDownloaderService friendService;
	private IFacebookSourceSelectorService sourceService;
	private List<FacebookFriend> friends;
	private Map<String, FacebookFriend> friendsMap;
	public FacebookSourceSelectorController()
	{
		String friendServiceSpring = new String("FacebookDownloader/spring-facebook-friend-downloader-service.xml");
		String sourceServiceSpring = new String("PrivateSourceSelector/spring-private-source-selector-service.xml");
		ApplicationContext context = new ClassPathXmlApplicationContext(friendServiceSpring,sourceServiceSpring);
		
		friendService = (IFacebookFriendDownloaderService) context.getBean("friendDownloaderService");
		sourceService = (IFacebookSourceSelectorService) context.getBean("privateSourceSelectorService");
		
		friends = new ArrayList<FacebookFriend>();
		friendsMap = new HashMap<String, FacebookFriend>();
	}
	
	public Iterable<String> getFriendNames(String facebookUserId)
	{
		System.out.println("Returning the facebook friends");
		List<FacebookFriend> tmpFriends = (List<FacebookFriend>) friendService.getFacebookFriends(facebookUserId);
		Set<String> friendNames = new HashSet<String>();
		int counter = 0;
		for(FacebookFriend friend : tmpFriends)
		{
			System.out.println("Adding the facebook friends");
			String name = friend.getName();
			friendNames.add(name);
			friends.add(friend);
			friendsMap.put(name, friend);
			counter++;
			System.out.println("Name: "+name);
		}
		return friendNames;
	}
	
	public void saveFriendSources(Iterable<Integer> indices, String appUserId)
	{
		Set<SourceCreateDto> sourceDtos = new HashSet<SourceCreateDto>();
		for(int index : indices)
		{
			FacebookFriend friend = friendsMap.get(index);
			SourceCreateDto sourceDto = new SourceCreateDto(appUserId, friend.getId(), "User");
			sourceDtos.add(sourceDto);
		}
		sourceService.saveSources(sourceDtos);
	}
	
	public void saveFriendSourcesByNames(Iterable<String> names, String appUserId)
	{
		Set<SourceCreateDto> sourceDtos = new HashSet<SourceCreateDto>();
		for(String name : names)
		{
			FacebookFriend friend = friendsMap.get(name);
			SourceCreateDto sourceDto = new SourceCreateDto(appUserId, friend.getId(), "User");
			sourceDtos.add(sourceDto);
		}
		sourceService.saveSources(sourceDtos);
	}
	
	public void downloadFacebookFriends(String facebookUserId)
	{
		friendService.downloadFacebookFriends(facebookUserId);
	}
}
