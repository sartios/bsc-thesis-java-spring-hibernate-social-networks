package com.sones.sharedDto.facebook.keywordSearcher.feeds;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PhotoSearchDtoTester 
{
	@Test
	public	void	containsIntoName()
	{
		PhotoSearchDto	photo	=	new	PhotoSearchDto();
		photo.setName( "Contains the keyword" );
		assertTrue(photo.contains("keyword"));
	}
	
	@Test
	public	void	containsIntoNameDifferentCase()
	{
		PhotoSearchDto	photo	=	new	PhotoSearchDto();
		photo.setName( "Contains the keyword" );
		assertTrue(photo.contains("KeyWoRd"));
	}
	
	@Test
	public	void	containsIntoNameManySpaces()
	{
		PhotoSearchDto	photo	=	new	PhotoSearchDto();
		photo.setName( "Contains the keyword" );
		assertTrue(photo.contains("   keyword   "));
	}
	
	@Test
	public	void	containsIntoNameManySpecialCharacters()
	{
		PhotoSearchDto	photo	=	new	PhotoSearchDto();
		photo.setName( "Contains the keyword !@#$%^&*()" );
		assertTrue(photo.contains("!@#$%^&*()"));
	}
}
