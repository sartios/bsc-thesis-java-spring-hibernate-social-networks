package com.sones.sharedDto.facebook.keywordSearcher.feeds;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NoteSearchDtoTester 
{
	@Test
	public	void	containsIntoMessage()
	{
		NoteSearchDto	note	=	new	NoteSearchDto();
		note.setMessage( "Contains the keyword" );
		assertTrue(note.contains("keyword"));
	}
	
	@Test
	public	void	containsIntoMessageDifferentCase()
	{
		NoteSearchDto	note	=	new	NoteSearchDto();
		note.setMessage( "Contains the keyword" );
		assertTrue(note.contains("KeyWoRd"));
	}
	
	@Test
	public	void	containsIntoMessageManySpaces()
	{
		NoteSearchDto	note	=	new	NoteSearchDto();
		note.setMessage( "Contains the keyword" );
		assertTrue(note.contains("   keyword   "));
	}
	
	@Test
	public	void	containsIntoMessageManySpecialCharacters()
	{
		NoteSearchDto	note	=	new	NoteSearchDto();
		note.setMessage( "Contains the keyword !@#$%^&*()" );
		assertTrue(note.contains("!@#$%^&*()"));
	}
	
	@Test
	public	void	containsIntoSubject()
	{
		NoteSearchDto	note	=	new	NoteSearchDto();
		note.setSubject( "Contains the keyword" );
		assertTrue(note.contains("keyword"));
	}
	
	@Test
	public	void	containsIntoSubjectDifferentCase()
	{
		NoteSearchDto	note	=	new	NoteSearchDto();
		note.setSubject( "Contains the keyword" );
		assertTrue(note.contains("KeyWoRd"));
	}
	
	@Test
	public	void	containsIntoSubjectManySpaces()
	{
		NoteSearchDto	note	=	new	NoteSearchDto();
		note.setSubject( "Contains the keyword" );
		assertTrue(note.contains("   keyword   "));
	}
	
	@Test
	public	void	containsIntoSubjectManySpecialCharacters()
	{
		NoteSearchDto	note	=	new	NoteSearchDto();
		note.setSubject( "Contains the keyword !@#$%^&*()" );
		assertTrue(note.contains("!@#$%^&*()"));
	}
	
	@Test
	public	void	containsIntoMessageAndSubject()
	{
		NoteSearchDto	note	=	new	NoteSearchDto();
		note.setSubject( "Contains the keyword" );
		note.setMessage( "Contains the keyword" );
		assertTrue(note.contains("keyword"));
	}
	
	@Test
	public	void	containsIntoMessageAndSubjectDifferentCase()
	{
		NoteSearchDto	note	=	new	NoteSearchDto();
		note.setSubject( "Contains the keyword" );
		note.setMessage( "Contains the keyword" );
		assertTrue(note.contains("KeyWoRd"));
	}
	
	@Test
	public	void	containsIntoMessageAndSubjecManySpaces()
	{
		NoteSearchDto	note	=	new	NoteSearchDto();
		note.setSubject( "Contains the keyword" );
		note.setMessage( "Contains the keyword" );
		assertTrue(note.contains("   keyword   "));
	}
	
	@Test
	public	void	containsIntoMessageAndSubjecManySpecialCharacters()
	{
		NoteSearchDto	note	=	new	NoteSearchDto();
		note.setSubject( "Contains the keyword !@#$%^&*()" );
		note.setMessage( "Contains the keyword !@#$%^&*()" );
		assertTrue(note.contains("!@#$%^&*()"));
	}
}
