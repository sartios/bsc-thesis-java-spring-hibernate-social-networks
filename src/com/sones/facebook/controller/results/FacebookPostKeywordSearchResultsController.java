package com.sones.facebook.controller.results;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.controller.posts.FacebookPostController;
import com.sones.facebook.keyword.result.logic.IFaceboookPostKeywordResultService;
import com.sones.sharedDto.facebook.feed.FacebookPostIdDto;
import com.sones.sharedDto.facebook.result.FacebookResultViewDto;
import com.sones.usermanager.model.ApplicationUser;

public class FacebookPostKeywordSearchResultsController 
{
	private IFaceboookPostKeywordResultService service;
	private List<FacebookResultViewDto> resultDtos;
	private FacebookPostController postController;
	private final Logger _LOGGER;
	
	public FacebookPostKeywordSearchResultsController()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("FacebookResults/spring-facebookresults-logic.xml");
		service = (IFaceboookPostKeywordResultService) context.getBean( "facebookKeywordSearchResultService" );
		resultDtos = new ArrayList<FacebookResultViewDto>();
		postController = new FacebookPostController();
		_LOGGER = Logger.getLogger(FacebookPostKeywordSearchResultsController.class);
	}
	
	public String[] getColumnNames()
	{
		String[] columnNames = {"Description","Keywords","Type"};
		return columnNames;
	}
	
	public List< Vector< String > > getFacebookResults(ApplicationUser appUser)
	{
		List<FacebookResultViewDto> results = service.getFacebookKeywordResults(appUser);
		List< Vector< String > > rows = new LinkedList<Vector<String>>();
		for(FacebookResultViewDto resultDto : results)
		{
			Vector<String> row = new Vector<String>();
			String description = resultDto.getDescripton();
			row.add( description );
			
			String keywords = "[ ";
			for( String value : resultDto.getKeywordValues() )
			{
				keywords += value + ", ";
			}
			keywords += "]";
			row.add(keywords);
			
			String type = resultDto.getType();
			row.add(type);
			rows.add(row);
			
			resultDtos.add(resultDto);
		}
		return rows;
	}
	
	public void showFacebookPost(int position)
	{
		_LOGGER.warn("Result from position " + position);
		FacebookResultViewDto result = resultDtos.get(position);
		FacebookPostIdDto idDto = result.getId();
		String id = idDto.getId();
		String type = result.getType();
		if(type.equals("StatusMessage"))
		{
			_LOGGER.warn("Show status message");
			postController.showStatusMessage(id);
		}
		if(type.equals("Link"))
		{
			_LOGGER.warn("Show link");
			postController.showLink(id);
		}
		if(type.equals("Photo"))
		{
			_LOGGER.warn("Show photo");
			postController.showPhoto(id);
		}
		if(type.equals("Video"))
		{
			_LOGGER.warn("Show video");
			postController.showVideo(id);
		}
	}
}
