package com.sones.businessLogic.Facebook;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LinkTest {
	
	private Link link_;
	private String feedId_;
	private String userId_;
	private String name_;
	private String caption_;
	private String description_;
	private String message_;
	private String photoURL_;
	private String linkURL_; 

	
	@Before
	public void setUp(){
		link_=new Link();
		feedId_="ID of link";
		userId_="Creator of link";
		name_="Test Link";
		caption_="Caption of link";
		description_="Description of link";
		message_="Message of link";
		photoURL_="Photo of link";
		linkURL_="Url of link"; 
	}
	
	@Test
	public void LinkConstructorNoArgs_CreateUsefullLink_Test(){
		Link link = new Link();
		boolean isThrowing=false;
		try{
			link.getCaption().isEmpty();
			link.getName().isEmpty();
			link.getDescription().isEmpty();
			link.getMessage().isEmpty();
			link.getPhotoURL().isEmpty();
			link.getLinkURL().isEmpty();
		}
		catch(NullPointerException ex){
			isThrowing=true;
		}
		assertFalse(isThrowing);
	}

	@Test
	public void linkConstructorWithArgs_CreatesCorrectLink_Test(){
		link_ = new Link(name_, caption_, description_, message_, feedId_, userId_);
		assertEquals(link_.getName(), name_);
		assertEquals(link_.getCaption(), caption_);
		assertEquals(link_.getDescription(), description_);
		assertEquals(link_.getMessage(), message_);
		assertEquals(link_.getId_(), feedId_);
		assertEquals(link_.getFromId_(), userId_);
	}
	
	@Test
	public void setName_NormalName_Test(){
		String name="name";
		link_.setName(name);
		assertEquals(link_.getName(), name);
	}
	
	@Test
	public void setName_NameIsNull_Test(){
		String name=null;
		link_.setName(name);
		assertTrue(link_.getName().isEmpty());
	}
	
	@Test
	public void setCaption_NormalCaption_Test(){
		String caption="caption";
		link_.setCaption(caption);
		assertEquals(link_.getCaption(), caption);
	}
	
	@Test
	public void setCaption_CaptionIsNull_Test(){
		String caption=null;
		link_.setCaption(caption);
		assertTrue(link_.getCaption().isEmpty());
	}
	
	@Test
	public void setDescription_NormalDescription_Test(){
		String description="Description";
		link_.setDescription(description);
		assertEquals(link_.getDescription(), description);
	}
	
	@Test
	public void setDescription_DescriptionIsNull_Test(){
		String description=null;
		link_.setDescription(description);
		assertTrue(link_.getDescription().isEmpty());
	}
	
	@Test
	public void setMessage_NormalMessage_Test(){
		String message="Description";
		link_.setMessage(message);
		assertEquals(link_.getMessage(), message);
	}
	
	@Test
	public void setMessage_MessageIsNull_Test(){
		String message=null;
		link_.setMessage(message);
		assertTrue(link_.getMessage().isEmpty());
	}
	
	@Test
	public void setPhotoURL_NormalPhotoURL_Test(){
		String photoURL="PhotoURL";
		link_.setPhotoURL(photoURL);
		assertEquals(link_.getPhotoURL(), photoURL);
	}
	
	@Test
	public void setPhotoURL_PhotoURLIsNull_Test(){
		String photoURL=null;
		link_.setPhotoURL(photoURL);
		assertTrue(link_.getPhotoURL().isEmpty());
	}
	
	@Test
	public void setLinkURL_NormalLinkURL_Test(){
		String linkURL="LinkURL";
		link_.setLinkURL(linkURL);
		assertEquals(link_.getLinkURL(), linkURL);
	}
	
	@Test
	public void setLinkURL_PhotoURLIsNull_Test(){
		String linkURL=null;
		link_.setLinkURL(linkURL);
		assertTrue(link_.getLinkURL().isEmpty());
	}
	
	@Test
	public void find_KeywordInSameCase_Test(){
		String keyword="keyword";
		link_.setCaption(keyword);
		assertTrue(link_.find(keyword));
	}
	
	@Test
	public void find_KeywordHasSpace_Test(){
		String keyword="keyword";
		link_.setCaption(keyword+" ");
		assertTrue(link_.find(keyword));
	}
	
	@Test
	public void find_KeywordInUpperCase_Test(){
		String keyword="keyword";
		link_.setCaption("KEYWORD");
		assertTrue(link_.find(keyword));
	}
	
	@Test
	public void find_KeywordIsMixCase_Test(){
		String keyword="kEyWoRd";
		link_.setCaption("keyword");
		assertTrue(link_.find(keyword));
		
	}
}
