package com.sones.facebook.dao.source;

import com.sones.dao.IGenericDao;
import com.sones.facebook.model.source.Source;
import com.sones.facebook.model.source.SourceType;

/**
 * Provides methods for accessing {@link Source} class.
 * @author sartios.sones@gmail.com.
 *
 */
public interface ISourceDao extends IGenericDao<Source, String>
{
	public Iterable<Source> GetByType( SourceType type );
}
