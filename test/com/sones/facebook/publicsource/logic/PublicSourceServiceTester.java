package com.sones.facebook.publicsource.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.publicsource.dao.ICriteriaDao;
import com.sones.facebook.publicsource.model.Criteria;
import com.sones.facebook.tokenmanager.model.FacebookToken;
import com.sones.usermanager.model.ApplicationUser;

public class PublicSourceServiceTester 
{
	private	IPublicSourceService service;
	private	FacebookToken token;
	private	List< Criteria > criterias;
	private ApplicationUser appUser;
	private	ICriteriaDao criteriaDao;
	
	private ApplicationContext context;
	
	
	public PublicSourceServiceTester()
	{
		context = new ClassPathXmlApplicationContext( "PublicSources/spring-publicplace-logic-nu.xml" );
	}
	
	@Before
	public void setUp()
	{
		token = new FacebookToken();
		token.setValue("access_token=AAAAAAITEghMBABt6NNnp0XwkoMEqHV8qmH6qBm6yo5HTUmaxmdeWdgOhcTC2UyqBpvN67ZBWmEDwPwev5BUchRZAIE9s58SFcA3j8LIZCrBfvtk3htK");
		
		criterias = getCriterias();
		appUser = new ApplicationUser();
		
		service = ( PublicSourceService ) context.getBean( "service" );		
	}
	
	private List<Criteria> getCriterias() 
	{
		List< Criteria > list = new ArrayList<Criteria>();
		list.add( new Criteria( "coffee" ) );
		list.add( new Criteria( "party" ) );
		list.add( new Criteria( "sport" ) );
		list.add( new Criteria( "crossfit" ) );
		list.add( new Criteria( "bodybuilding" ) );
		list.add( new Criteria( "football" ) );
		
		return list;
	}

	@Test
	public void testDownloadPublicPlaces()
	{
		service.DownloadPublicPlaces(criterias, token, appUser);
	}
	
	@Test
	public void testDownloadPublicPlacesWithDuplicateCriteria()
	{
		service.DownloadPublicPlaces(criterias, token, appUser);
	}
	
	@Test( expected = IllegalArgumentException.class )
	public void testDownloadPublicPlacesWithNullCriteria()
	{
		service.DownloadPublicPlaces(null, token, appUser);
	}
	
	@Test( expected = IllegalArgumentException.class )
	public void testDownloadPublicPlacesWithNullToken()
	{
		criterias.add( new Criteria( "coffee" ) );
		criterias.add( new Criteria( "party" ) );
		criterias.add( new Criteria( "sport" ) );
		criterias.add( new Criteria( "crossfit" ) );
		criterias.add( new Criteria( "bodybuilding" ) );
		criterias.add( new Criteria( "football" ) );
		service.DownloadPublicPlaces(criterias, null, appUser);
	}
	
	@Test( expected = IllegalArgumentException.class )
	public void testDownloadPublicPlacesWithNullApplicationUser()
	{
		service.DownloadPublicPlaces(criterias, token, null);
	}
}
