package com.sones.sharedDto.facebook.view.posts;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.Test;

import com.sones.facebook.model.feed.StatusMessage;
import com.sones.facebook.model.source.User;

public class StatusMessageViewDtoTester 
{
	private final String MESSAGE = "Status message for mapping";
	private final String USER_NAME = "Username";
	private StatusMessage statusMessage = new StatusMessage();
	private User user = new User();
	
	private DozerBeanMapper mapper;
	
	public StatusMessageViewDtoTester()
	{
		List<String> mappingFiles = new  ArrayList<String>();
		mappingFiles.add("Dozer/facebook/view/posts/ViewPostsMapper.xml");
		mapper = new DozerBeanMapper(mappingFiles);
	}
	
	@Test
	public void mapStatusMessageToStatusMessageViewDto()
	{
		statusMessage.setMessage(MESSAGE);
		user.setUsername(USER_NAME);
		statusMessage.setUser(user);
		StatusMessageViewDto postDto = new StatusMessageViewDto();
		mapper.map(statusMessage, postDto);
		assertEquals(statusMessage.getMessage(), postDto.getMessage());
		assertEquals(user.getUsername(), postDto.getUser().getUsername());
	}
}
