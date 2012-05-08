package com.sones.sharedDto.facebook.GraphApi.Wall;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.model.feed.Checkin;
import com.sones.facebook.placemanager.model.Place;

public class WallFacebookCheckinCreateDtoTester 
{
	private ApplicationContext context;
	private DozerBeanMapper mapper;
	private Checkin checkin;
	private WallCheckinCreateDto checkinDto;
	
	public WallFacebookCheckinCreateDtoTester()
	{
		context = new ClassPathXmlApplicationContext("spring-wall-sharedDto-nu.xml");
		List<String> mappingFiles = new  ArrayList<String>();
		mappingFiles.add("Dozer/facebook/GraphApi/Wall/CreateWallMapper.xml");
		mapper = new DozerBeanMapper(mappingFiles);
	}
	
	@Test
	public void testMappFromModelWorksCorrectlyForValidValues()
	{	
		Place place = new Place();
		place.setId("place id");
		place.setName("place name");
		place.setLatitude("place latitude");
		place.setLongitude("place longitude");
		place.setNumberOfCheckins(15);
		checkin = new Checkin();
		checkin.setPlace(place);
		checkin.setId("checkinID");
		checkin.setMessage("checkin message");
		checkin.setCreatedDate(Calendar.getInstance().getTime());
		
		checkinDto = new WallCheckinCreateDto();
		mapper.map(checkin, checkinDto);
		
		assertEquals(checkin.getId(),checkinDto.getId().getId());
		assertEquals(checkin.getMessage(),checkinDto.getMessage());
		assertEquals(checkin.getCreatedDate(),checkinDto.getDate());
		assertEquals(checkin.getPlace().getId(),checkinDto.getPlace().getId().getId());
		assertEquals(checkin.getPlace().getLatitude(),checkinDto.getPlace().getLatitude());
		assertEquals(checkin.getPlace().getLongitude(),checkinDto.getPlace().getLongitude());
		assertEquals(checkin.getPlace().getName(),checkinDto.getPlace().getName());
	}
}
