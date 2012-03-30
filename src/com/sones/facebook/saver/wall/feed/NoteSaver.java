package com.sones.facebook.saver.wall.feed;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.sones.facebook.dao.feed.INoteDao;
import com.sones.facebook.model.feed.Note;
import com.sones.facebook.model.feed.comment.Comment;

public class NoteSaver implements INoteSaver
{

	private	final Logger _LOGGER;
	private	INoteDao noteDao;
	private	ICommentSaver	commentSaver;
	
	public NoteSaver()
	{
		_LOGGER = Logger.getLogger(NoteSaver.class);
	}
	
	@Override
	public void Save(Note note)
	{
		if( note == null )
		{
			_LOGGER.error("Note object can't be null!");
			throw new NullPointerException("Link object can't be null!");
		}
		try
		{
			if( isAnExistingNote( note ) == false )
			{
				if( hasComments( note ) )
				{
					Set< Comment >	comments	=	note.getComments();
					note.setComments(null);
					noteDao.Save( note );
					commentSaver.Save(comments);
				}
				else
				{
					noteDao.Save( note );
				}
			}
		}
		catch (	DataAccessException e)
		{
			_LOGGER.error("Unable to save note object because "
					+e.getMessage().toString());
			throw e;
		}	
	}

	public INoteDao getNoteDao()
	{
		return noteDao;
	}

	public void setNoteDao(INoteDao noteDao) 
	{
		this.noteDao = noteDao;
	}

	/**
	 * @param commentSaver the commentSaver to set
	 */
	public void setCommentSaver(ICommentSaver commentSaver) {
		this.commentSaver = commentSaver;
	}

	/**
	 * @return the commentSaver
	 */
	public ICommentSaver getCommentSaver() {
		return commentSaver;
	}
	
	private	boolean	isAnExistingNote( Note note )
	{
		Note	dbNote	=	noteDao.GetById( note.getId() );
		if( dbNote == null )
		{
			return	false;
		}
		return	true;
	}
	
	private	boolean	hasComments( Note note )
	{
		Set< Comment >	comments	=	note.getComments();
		if( comments == null )
		{
			return	false;
		}
		if( comments.size() == 0 )
		{
			return	false;
		}
		return	true;
	}
}
