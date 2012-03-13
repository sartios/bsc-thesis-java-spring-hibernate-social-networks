package com.sones.facebook.dao.source;

import com.sones.dao.IGenericDao;
import com.sones.facebook.model.source.SourceType;

/**
 * Provides methods for accessing {@link SourceType} class.
 * @author sartios.sones@gmail.com.
 *
 */
public interface ISourceTypeDao extends IGenericDao<SourceType, String>
{
	/**
	 * Finds the source type by type.
	 * @param sourceType the type of the source type.
	 * @return SourceType object.
	 * @throws IllegalArgumentException when 
	 * <ul>
	 * 	<li>Source type param is null.</li>
	 *  <li>Source type type value is null or empty.</li>
	 * </ul>
	 * @throws NullPointerException when there isn't a source type for the specific type.
	 */
	public SourceType GetByType(SourceType sourceType);
}
