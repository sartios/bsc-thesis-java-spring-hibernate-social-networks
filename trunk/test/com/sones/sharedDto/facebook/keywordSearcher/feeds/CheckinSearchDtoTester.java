package com.sones.sharedDto.facebook.keywordSearcher.feeds;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CheckinSearchDtoTester 
{
	@Test
	public	void	containsIntoMessageWithNullMessage()
	{
		CheckinSearchDto	checkin	=	new	CheckinSearchDto();
		checkin.setMessage( null );
		assertFalse(checkin.contains("keyword"));
	}
	
	@Test
	public	void	containsIntoMessage()
	{
		CheckinSearchDto	checkin	=	new	CheckinSearchDto();
		checkin.setMessage( "Contains the keyword" );
		assertTrue(checkin.contains("keyword"));
	}
	
	@Test
	public	void	containsIntoMessageDifferentCase()
	{
		CheckinSearchDto	checkin	=	new	CheckinSearchDto();
		checkin.setMessage( "Contains the keyword" );
		assertTrue(checkin.contains("KeyWoRd"));
	}
	
	@Test
	public	void	containsIntoMessageManySpaces()
	{
		CheckinSearchDto	checkin	=	new	CheckinSearchDto();
		checkin.setMessage( "Contains the keyword" );
		assertTrue(checkin.contains("   keyword   "));
	}
	
	@Test
	public	void	containsIntoMessageManySpecialCharacters()
	{
		CheckinSearchDto	checkin	=	new	CheckinSearchDto();
		checkin.setMessage( "Contains the keyword !@#$%^&*()" );
		assertTrue(checkin.contains("!@#$%^&*()"));
	}
}
