package com.sones.businessLogic.Facebook;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FeedTest {
	
	private Feed feed_;
	private String userId_;
	private String feedId_;
	private int numOfCom_;
	@Before
	public void setUp(){
		userId_="1";
		feedId_="2";
		numOfCom_=0;
		feed_ = new Feed(feedId_, userId_, numOfCom_);
	}
	
	@After
	public void tearDown(){
		feed_=null;
	}
	
	@Test
	public void constructorWithoutArguments_CreatesFeed_Test(){
		Feed feed1 = new Feed();
		boolean doesNotThrowException=true;
		try{
			String id=feed1.getId_();
			id.isEmpty();
			
			String fromID=feed1.getFromId_();
			fromID.isEmpty();
			
			String createdTime=feed1.getCreatedTime_();
			createdTime.isEmpty();
			
			int numOfCom=feed1.getNumberOfComments_();
			assertNotNull(numOfCom);
			
			int numOfLikes=feed1.getNumberOfLikes_();
			assertNotNull(numOfLikes);

			String type=feed1.getType();
			type.isEmpty();
			
			Set comments=feed1.getComments_();
			comments.isEmpty();
		}
		catch(NullPointerException ex){
			doesNotThrowException=false;
		}
		assertTrue(doesNotThrowException);
	}
	
	@Test
	public void constructorsWithArguments_SetArguments_Test(){
		String userId="1";
		String feedId="2";
		int numOfCom=0;
		
		Feed feed1 = new Feed(feedId, userId);
		assertEquals(feed1.getId_(), feedId);
		assertEquals(feed1.getFromId_(), userId);
		
		feed1 = new Feed(feedId, userId, numOfCom);
		
		assertEquals(feed1.getId_(), feedId);
		assertEquals(feed1.getFromId_(), userId);
		assertEquals(feed1.getNumberOfComments_(), numOfCom);
		

		Feed f2 = new Feed(feed1);
		assertEquals(feed1, f2);
	}

	@Test
	public void getID_ReturnID_Test(){
		assertEquals(feed_.getId_(), feedId_);
	}
	
	@Test
	public void setID_NormalID_Test(){
		String normal="normal";
		feed_.setId_(normal);
		assertEquals(feed_.getId_(),normal);
	}
	
	@Test
	public void setID_NullID_Test(){
		String nullID=null;
		feed_.setId_(nullID);
		assertTrue(feed_.getId_().isEmpty());
	}
	
	@Test
	public void getFromID_ReturnFromID_Test(){
		assertEquals(feed_.getFromId_(), userId_);
	}
	
	@Test
	public void setFromID_NormalFromID_Test(){
		String normal="normal";
		feed_.setFromId_(normal);
		assertEquals(feed_.getFromId_(),normal);
	}
	
	@Test
	public void setFromID_NullID_Test(){
		String nullID=null;
		feed_.setFromId_(nullID);
		assertTrue(feed_.getFromId_().isEmpty());
	}
	
	@Test
	public void getNumberOfComments_ReturnNumberOfComments_Test(){
		assertEquals(feed_.getNumberOfComments_(), numOfCom_);
	}
	
	@Test
	public void setNumberOfComments_PositiveNumber_Test(){
		int aPositiveNumber=15;
		feed_.setNumberOfComments_(aPositiveNumber);
		assertEquals(feed_.getNumberOfComments_(), aPositiveNumber);
	}
	
	@Test
	public void setNumberOfComments_NegativeNumber_Test(){
		int aNegativeNumber=-1;
		int zero=0;
		feed_.setNumberOfComments_(aNegativeNumber);
		assertEquals(feed_.getNumberOfComments_(), zero);
	}
	
	@Test
	public void setNumberOfComments_LimitPositiveInteger_Test(){
		int limitPositiveInteger=Integer.MAX_VALUE;
		feed_.setNumberOfComments_(limitPositiveInteger);
		assertEquals(feed_.getNumberOfComments_(), limitPositiveInteger);
	}
	
	@Test
	public void getCreatedTime_NormalTime_Test(){
		String createdTime="xxxx/xx/xx";
		feed_.setCreatedTime_(createdTime);
		assertEquals(feed_.getCreatedTime_(), createdTime);
	}
	
	@Test
	public void setCreatedTime_NullTime_Test(){
		String createdTime=null;
		feed_.setCreatedTime_(createdTime);
		assertEquals(feed_.getCreatedTime_(), "");
	}
	
	@Test
	public void getComments_ThereAreDifferentComments_Test(){
		Set<String> comments = new HashSet<String>();
		comments.add("1");
		comments.add("2");
		comments.add("3");
		feed_.setComments_(comments);
		assertEquals(comments.size(), feed_.getComments_().size());
	}
	
	@Test
	public void getComments_TheCommentsAreSame_Test(){
		Set<String> comments = new HashSet<String>();
		comments.add("1");
		comments.add("1");
		comments.add("1");
		feed_.setComments_(comments);
		assertEquals(1, feed_.getComments_().size());
	}
	
	@Test
	public void setComments_NullComments_Test(){
		feed_.setComments_(null);
		assertEquals(0, feed_.getComments_().size());
	}
	
	@Test
	public void getNumberOfLikes_ReturnNumberOfLikes_Test(){
		feed_.setNumberOfLikes_(numOfCom_);
		assertEquals(feed_.getNumberOfComments_(), numOfCom_);
	}
	
	@Test
	public void setNumberOfLikes_PositiveNumber_Test(){
		int aPositiveNumber=15;
		feed_.setNumberOfLikes_(aPositiveNumber);
		assertEquals(feed_.getNumberOfLikes_(), aPositiveNumber);
	}
	
	@Test
	public void setNumberOfLikes_NegativeNumber_Test(){
		int aNegativeNumber=-1;
		int zero=0;
		feed_.setNumberOfLikes_(aNegativeNumber);
		assertEquals(feed_.getNumberOfLikes_(), zero);
	}
	
	@Test
	public void setNumberOfLikes_LimitPositiveInteger_Test(){
		int limitPositiveInteger=Integer.MAX_VALUE;
		feed_.setNumberOfLikes_(limitPositiveInteger);
		assertEquals(feed_.getNumberOfLikes_(), limitPositiveInteger);
	}
	
	@Test
	public void equals_SameFeedID_Test(){
		Feed feed2 = new Feed(feedId_, userId_);
		assertTrue(feed_.equals(feed2));
	}
	
	@Test
	public void equals_NotSameFeedID_Test(){
		Feed feed2 = new Feed("xxxx", userId_);
		assertFalse(feed_.equals(feed2));
	}
	
	@Test
	public void getType_ReturnType_Test(){
		String type="type";
		feed_.setType(type);
		assertEquals(feed_.getType(), type);
	}
	
	@Test
	public void setType_NormalType_Test(){
		String normal="normal";
		feed_.setType(normal);
		assertEquals(feed_.getType(),normal);
	}
	
	@Test
	public void setType_NullType_Test(){
		String nullType=null;
		feed_.setType(nullType);
		assertTrue(feed_.getType().isEmpty());
	}
}
