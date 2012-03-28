package com.sones.sharedDto.facebook.keywordSearcher.feeds;

import static org.junit.Assert.*;

import org.junit.Test;


public class StatusMessageSearchDtoTester 
{
	@Test
	public	void	containsIntoMessageWithNullMessage()
	{
		StatusMessageSearchDto	statusMessage	=	new	StatusMessageSearchDto();
		statusMessage.setMessage( null );
		assertFalse(statusMessage.contains("keyword"));
	}
	
	@Test
	public	void	containsIntoMessage()
	{
		StatusMessageSearchDto	statusMessage	=	new	StatusMessageSearchDto();
		statusMessage.setMessage( "Contains the keyword" );
		assertTrue(statusMessage.contains("keyword"));
	}
	
	@Test
	public	void	containsIntoMessageDifferentCase()
	{
		StatusMessageSearchDto	statusMessage	=	new	StatusMessageSearchDto();
		statusMessage.setMessage( "Contains the keyword" );
		assertTrue(statusMessage.contains("KeyWoRd"));
	}
	
	@Test
	public	void	containsIntoMessageManySpaces()
	{
		StatusMessageSearchDto	statusMessage	=	new	StatusMessageSearchDto();
		statusMessage.setMessage( "Contains the keyword" );
		assertTrue(statusMessage.contains("   keyword   "));
	}
	
	@Test
	public	void	containsIntoMessageManySpecialCharacters()
	{
		StatusMessageSearchDto	statusMessage	=	new	StatusMessageSearchDto();
		statusMessage.setMessage( "Contains the keyword !@#$%^&*()" );
		assertTrue(statusMessage.contains("!@#$%^&*()"));
	}
}
