/*package com.sones.businessLogic;

import org.junit.Test;

import com.sones.businessLogic.v2.Keyword;

import static org.junit.Assert.*;

public class KeywordListTest {

	*//**
	 * Test if we can add the same keyword twice
	 *//*
	@Test
	public void addKeyword_twice_Test(){
		KeywordList list = new KeywordList();
		Keyword keyword = new Keyword("keyword");
 		list.addKeyword(keyword);
 		assertFalse(list.addKeyword(keyword));
	}
	
	*//**
	 * Test if we can add the same keyword with diffirent case
	 *//*
	@Test
	public void addKeyword_CaseSensitive_Test(){
		KeywordList list = new KeywordList();
		Keyword keyword = new Keyword("keyword");
 		list.addKeyword(new Keyword("KEYWORD"));
 		assertFalse(list.addKeyword(keyword));
	}
	
	*//**
	 * Test if we can add the same keyword with blank space around
	 *//*
	@Test
	public void addKeyword_BlankSpace_Test(){
		KeywordList list = new KeywordList();
		Keyword keyword = new Keyword("keyword");
 		list.addKeyword(keyword);
 		assertFalse(list.addKeyword(new Keyword(keyword.getValue()+"  ")));
	}
	
	*//**
	 * Test if we can get more keywords than exist
	 *//*
	@Test
	public void getKeyword_MoreThanExist_Test(){
		KeywordList list = new KeywordList();
		Keyword keyword = new Keyword("keyword");
		boolean returnsNull=false;
		for(int i=0;i<3;i++){
			list.addKeyword(new Keyword(keyword.getValue()+i));
		}
		for(int i=0;i<10;i++){
			if(list.getKeyword()==null){
				returnsNull=true;
			}
		}
		assertTrue(returnsNull);
	}
}
*/