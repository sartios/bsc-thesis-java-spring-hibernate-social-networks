package com.sones.businessLogic;

import org.junit.Test;
import static org.junit.Assert.*;

public class KeywordListTest {

	/**
	 * Test if we can add the same keyword twice
	 */
	@Test
	public void addKeyword_twice_Test(){
		KeywordList list = new KeywordList();
		String keyword = "keyword";
 		list.addKeyword(keyword);
 		assertFalse(list.addKeyword(keyword));
	}
	
	/**
	 * Test if we can add the same keyword with diffirent case
	 */
	@Test
	public void addKeyword_CaseSensitive_Test(){
		KeywordList list = new KeywordList();
		String keyword = "keyword";
 		list.addKeyword(keyword.toLowerCase());
 		assertFalse(list.addKeyword(keyword.toUpperCase()));
	}
	
	/**
	 * Test if we can add the same keyword with blank space around
	 */
	@Test
	public void addKeyword_BlankSpace_Test(){
		KeywordList list = new KeywordList();
		String keyword = "keyword";
 		list.addKeyword(keyword);
 		assertFalse(list.addKeyword(keyword+"  "));
	}
	
	/**
	 * Test if we can get more keywords than exist
	 */
	@Test
	public void getKeyword_MoreThanExist_Test(){
		KeywordList list = new KeywordList();
		String keyword = "keyword";
		boolean returnsNull=false;
		for(int i=0;i<3;i++){
			list.addKeyword(keyword+i);
		}
		for(int i=0;i<10;i++){
			if(list.getKeyword()==null){
				returnsNull=true;
			}
		}
		assertTrue(returnsNull);
	}
}
