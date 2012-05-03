package com.sones.sharedDto.facebook.view.posts;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.Test;

import com.sones.facebook.model.feed.comment.Comment;
import com.sones.facebook.model.source.User;

public class CommentViewDtoTester 
{
	private Comment comment = new Comment();
	private User user = new User();
	
	private DozerBeanMapper mapper;
	
	public CommentViewDtoTester()
	{
		List<String> mappingFiles = new  ArrayList<String>();
		mappingFiles.add("Dozer/facebook/view/posts/ViewPostsMapper.xml");
		mapper = new DozerBeanMapper(mappingFiles);
	}
	
	@Test
	public void mapCorrectly()
	{
		comment.setMessage("Comment message");
		user.setUsername("Username");
		comment.setUser(user);
		CommentViewDto commentDto = new CommentViewDto();
		mapper.map(comment, commentDto);
		
		assertEquals(comment.getMessage(),commentDto.getValue());
		assertEquals(comment.getUser().getUsername(),commentDto.getUser().getUsername());
	}
}
