package com.sones.facebook.keywordSearcher.logic.retriever;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.sones.facebook.dao.feed.INoteDao;
import com.sones.facebook.downloader.model.FacebookPostDownload;
import com.sones.facebook.model.feed.Note;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.ISearchableFacebookFeed;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.NoteSearchDto;
import com.sones.userManager.model.ApplicationUser;

public class NoteSearchDataManager 	extends	AbstractDataManager	implements	INoteSearchDataManager
{
	/**
	 * Responsible for retrieving {@link Note}s.
	 */
	private	INoteDao	noteDao;
	
	/**
	 * The logger of the class.
	 */
	private	Logger	_LOGGER;
	
	/**
	 * Initializes the object.
	 */
	public	NoteSearchDataManager()
	{
		_LOGGER	=	Logger.getLogger( NoteSearchDataManager.class );
	}

	@Override
	public Iterable<ISearchableFacebookFeed> getNoteForSearch( ApplicationUser appUser ) 
	{
	
		Set<ISearchableFacebookFeed> posts = new HashSet<ISearchableFacebookFeed>();
		Set<FacebookPostDownload>	downloadedPosts	=	(Set<FacebookPostDownload>) getManager().getStatusMessagesForKeywordSearch(appUser);
		if( downloadedPosts != null )
		{
			for( FacebookPostDownload post : downloadedPosts )
			{
				Note	note = noteDao.GetById(post.getId().getPost().getId());
				if( note != null )
				{
					NoteSearchDto noteDto = new NoteSearchDto();
					getMapper().map(note, noteDto);
					posts.add(noteDto);
				}
			}
		}
		return posts;
		
	}

	public INoteDao getNoteDao() {
		return noteDao;
	}

	public void setNoteDao(INoteDao noteDao) {
		this.noteDao = noteDao;
	}

	@Override
	public Iterable<ISearchableFacebookFeed> getDataToBeSearched(
			ApplicationUser appUser) {
		return	getNoteForSearch(appUser);
	}

}
