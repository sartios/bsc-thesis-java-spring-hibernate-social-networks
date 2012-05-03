package com.sones.sharedDto.facebook.view.posts;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.Test;

import com.sones.facebook.model.feed.Video;
import com.sones.facebook.model.source.User;

public class VideoViewDtoTester 
{
	private Video video = new Video();
	private User user = new User();
	private DozerBeanMapper mapper;
	
	public VideoViewDtoTester()
	{
		List<String> mappingFiles = new  ArrayList<String>();
		mappingFiles.add("Dozer/facebook/view/posts/ViewPostsMapper.xml");
		mapper = new DozerBeanMapper(mappingFiles);
	}
	
	@Test
	public void mapCorrectly()
	{
		video.setName("Video name");
		video.setDescription("Video description");
		video.setPicture("Video picture");
		video.setSource("Video source");
		user.setUsername("Username");
		video.setUser(user);
		VideoViewDto postDto = new VideoViewDto();
		mapper.map(video, postDto);
		assertEquals(video.getName(),postDto.getName());
		assertEquals(video.getPicture(),postDto.getPicture());
		assertEquals(video.getSource(),postDto.getSource());
		assertEquals(video.getDescription(),postDto.getDescription());
		assertEquals(user.getUsername(),postDto.getUser().getUsername());
	}
}
