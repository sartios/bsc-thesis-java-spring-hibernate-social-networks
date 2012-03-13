package com.sones.facebook.post.saver.source;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.sones.facebook.dao.source.ISourceDao;
import com.sones.facebook.dao.source.ISourceTypeDao;
import com.sones.facebook.model.source.Source;
import com.sones.facebook.model.source.SourceType;

public class SourceSaver implements ISourceSaver
{

	private	final	Logger	_LOGGER;
	private	ISourceDao sourceDao;
	private	ISourceTypeDao sourceTypeDao;
	
	public SourceSaver()
	{
		_LOGGER = Logger.getLogger(SourceSaver.class);
		_LOGGER.warn("Initializing...");
	}
	
	@Override
	public void Save(Source source) 
	{
		if( source == null )
		{
			_LOGGER.error("Source can't be null!");
			throw new NullPointerException("Source can't be null!");
		}
		SourceType tmpSourceType = source.getType();
		
		SourceType sourceType;
		try
		{
			sourceType = sourceTypeDao.GetByType(tmpSourceType);
		}
		catch (NullPointerException e)
		{
			throw new IllegalArgumentException("The type "+tmpSourceType.getType()+" doesn't exist.");
		}
		source.setType(sourceType);
		try
		{
			sourceDao.SaveOrUpdate(source);
		}
		catch ( DataAccessException e) 
		{
			_LOGGER.error("Unable to save source id: "+source.getId());
			throw e;
		}
	}

	/**
	 * @param sourceDao the sourceDao to set
	 */
	public void setSourceDao(ISourceDao sourceDao) {
		this.sourceDao = sourceDao;
	}

	/**
	 * @return the sourceDao
	 */
	public ISourceDao getSourceDao() {
		return sourceDao;
	}

	/**
	 * @param sourceTypeDao the sourceTypeDao to set
	 */
	public void setSourceTypeDao(ISourceTypeDao sourceTypeDao) {
		this.sourceTypeDao = sourceTypeDao;
	}

	/**
	 * @return the sourceTypeDao
	 */
	public ISourceTypeDao getSourceTypeDao() {
		return sourceTypeDao;
	}

}
