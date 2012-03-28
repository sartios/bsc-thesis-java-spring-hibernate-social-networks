package com.sones.sharedDto.facebook.keywordSearcher.feeds;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LinkSearchDtoTester 
{
	@Test
	public	void	containsIntoMessage()
	{
		LinkSearchDto	link	=	new	LinkSearchDto();
		link.setMessage( "Contains the keyword" );
		assertTrue(link.contains("keyword"));
	}
	
	@Test
	public	void	containsIntoMessageDifferentCase()
	{
		LinkSearchDto	link	=	new	LinkSearchDto();
		link.setMessage( "Contains the keyword" );
		assertTrue(link.contains("KeyWoRd"));
	}
	
	@Test
	public	void	containsIntoMessageManySpaces()
	{
		LinkSearchDto	link	=	new	LinkSearchDto();
		link.setMessage( "Contains the keyword" );
		assertTrue(link.contains("   keyword   "));
	}
	
	@Test
	public	void	containsIntoMessageManySpecialCharacters()
	{
		LinkSearchDto	link	=	new	LinkSearchDto();
		link.setMessage( "Contains the keyword !@#$%^&*()" );
		assertTrue(link.contains("!@#$%^&*()"));
	}
	
	@Test
	public	void	containsIntoName()
	{
		LinkSearchDto	link	=	new	LinkSearchDto();
		link.setName( "Contains the keyword" );
		assertTrue( link.contains( "keyword" ) );
	}
	
	@Test
	public	void	containsIntoNameDifferentCase()
	{
		LinkSearchDto	link	=	new	LinkSearchDto();
		link.setName( "Contains the keyword" );
		assertTrue(link.contains("KeyWoRd"));
	}
	
	@Test
	public	void	containsIntoNameManySpaces()
	{
		LinkSearchDto	link	=	new	LinkSearchDto();
		link.setName( "Contains the keyword" );
		assertTrue(link.contains("   keyword   "));
	}
	
	@Test
	public	void	containsIntoNameManySpecialCharacters()
	{
		LinkSearchDto	link	=	new	LinkSearchDto();
		link.setName( "Contains the keyword !@#$%^&*()" );
		assertTrue(link.contains("!@#$%^&*()"));
	}
	
	@Test
	public	void	containsIntoMessageAndNameAndDescription()
	{
		LinkSearchDto	link	=	new	LinkSearchDto();
		link.setName( "Contains the keyword" );
		link.setMessage( "Contains the keyword" );
		link.setDescription( "Contains the keyword" );
		assertTrue(link.contains("keyword"));
	}
	
	@Test
	public	void	containsIntoMessageAndNameAndDescriptionDifferentCase()
	{
		LinkSearchDto	link	=	new	LinkSearchDto();
		link.setName( "Contains the keyword" );
		link.setMessage( "Contains the keyword" );
		link.setDescription( "Contains the keyword" );
		assertTrue(link.contains("KeyWoRd"));
	}
	
	@Test
	public	void	containsIntoMessageAndNameAndDescriptionManySpaces()
	{
		LinkSearchDto	link	=	new	LinkSearchDto();
		link.setName( "Contains the keyword" );
		link.setMessage( "Contains the keyword" );
		link.setDescription( "Contains the keyword" );
		assertTrue(link.contains("   keyword   "));
	}
	
	@Test
	public	void	containsIntoMessageAndNameAndDescriptionManySpecialCharacters()
	{
		LinkSearchDto	link	=	new	LinkSearchDto();
		link.setName( "Contains the keyword !@#$%^&*()" );
		link.setMessage( "Contains the keyword !@#$%^&*()" );
		link.setDescription( "Contains the keyword !@#$%^&*()" );
		assertTrue(link.contains("!@#$%^&*()"));
	}
	
	@Test
	public	void	containsIntoDescription()
	{
		LinkSearchDto	link	=	new	LinkSearchDto();
		link.setDescription( "Contains the keyword" );
		assertTrue( link.contains( "keyword" ) );
	}
	
	@Test
	public	void	containsIntoDescriptionDifferentCase()
	{
		LinkSearchDto	link	=	new	LinkSearchDto();
		link.setDescription( "Contains the keyword" );
		assertTrue(link.contains("KeyWoRd"));
	}
	
	@Test
	public	void	containsIntoDescriptionManySpaces()
	{
		LinkSearchDto	link	=	new	LinkSearchDto();
		link.setDescription( "Contains the keyword" );
		assertTrue(link.contains("   keyword   "));
	}
	
	@Test
	public	void	containsIntoDescriptionManySpecialCharacters()
	{
		LinkSearchDto	link	=	new	LinkSearchDto();
		link.setDescription( "Contains the keyword !@#$%^&*()" );
		assertTrue(link.contains("!@#$%^&*()"));
	}
}
