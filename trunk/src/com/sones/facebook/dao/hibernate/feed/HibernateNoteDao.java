package com.sones.facebook.dao.hibernate.feed;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.feed.INoteDao;
import com.sones.facebook.model.feed.Note;

public class HibernateNoteDao  extends HibernateGenericDao<Note, String> implements INoteDao
{
	public HibernateNoteDao()
	{
		super(Note.class);
	}
}
