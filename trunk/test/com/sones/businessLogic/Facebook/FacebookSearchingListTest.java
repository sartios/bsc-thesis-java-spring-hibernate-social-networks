package com.sones.businessLogic.Facebook;

import org.junit.Test;
import static org.junit.Assert.*;

public class FacebookSearchingListTest {

	/**
	 * We test the getID method if it works correct
	 */
	@Test
	public void getID_correctNumberOfIDs_Test(){
		FacebookSearchingList list = new FacebookSearchingList();
		list.addID("1");
		list.addID("2");
		list.addID("3");
		String id;
		int runningTimes=0;
		while((id=list.getID())!=null){
			runningTimes++;
		}
		assertEquals(runningTimes, 3);
	}
	
	/**
	 * We test the reset method if it works correct.
	 */
	@Test
	public void reset_Test(){
		FacebookSearchingList list = new FacebookSearchingList();
		list.addID("1");
		list.addID("2");
		list.addID("3");
		String id;
		int runningTimes=0;
		while((id=list.getID())!=null){
			runningTimes++;
		}
		list.reset();
		while((id=list.getID())!=null){
			runningTimes++;
		}
		assertEquals(runningTimes, 6);
	}
	
}
