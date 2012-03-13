package com.sones.sharedDto.facebook.GraphApi.Wall;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.model.feed.Note;

public class WallFacebookNoteCreateDtoTester
{
	private ApplicationContext context;
	private DozerBeanMapper mapper;
	
	public WallFacebookNoteCreateDtoTester()
	{
		context = new ClassPathXmlApplicationContext("spring-wall-sharedDto-nu.xml");
		List<String> mappingFiles = new  ArrayList<String>();
		mappingFiles.add("Dozer/facebook/GraphApi/Wall/CreateWallMapper.xml");
		mapper = new DozerBeanMapper(mappingFiles);
	}
	
	@Test
	public	void	TestNote()
	{
		WallNoteCreateDto noteDto = (WallNoteCreateDto) context.getBean("noteDto");
		Note noteModel = mapper.map(noteDto, Note.class);
		
		Assert.assertEquals("Note id mapping has error",noteDto.getId().getId(), noteModel.getId());
		Assert.assertEquals("Note subject mapping has error",noteDto.getSubject(), noteDto.getSubject());
		Assert.assertEquals("Note message has error",noteDto.getMessage(), noteDto.getMessage());
	}
}
