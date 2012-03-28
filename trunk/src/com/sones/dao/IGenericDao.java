package com.sones.dao;

import org.hibernate.classic.Session;

/**
 * Generic DAO interface which provides basic methods for DAOs.
 * @author sartios.sones@gmail.com.
 *
 * @param <TModel> the type of the model.
 * @param <TId> the type of the id.
 */
public interface IGenericDao<TModel,TId>
{
	/**
	 * @return all the entries of the model.
	 */
	public	Iterable<TModel>	GetAll();
	
	/**
	 * @param from the position of the first entry.
	 * @param maxResults the number of maximum returned entries.
	 * @return all the entries of the model in a specified range.
	 */
	public	Iterable<TModel>	Get(int from, int maxResults);
	
	/**
	 * {@link Session#get(Object)}
	 * @param id the entry id.
	 * @return The entry with the specified id.
	 */
	public	TModel	GetById(TId id);
	
	/**
	 * {@link Session#load(Object)}
	 * @param id the entry id.
	 * @return The entry with the specified id.
	 */
	public	TModel	LoadById(TId id);
	
	/**
	 * {@link Session#save(Object)}
	 * @param model The model to save.
	 */
	public	void	Save(TModel model);
	
	/**
	 * {@link Session#update(Object)}
	 * @param model The model to update.
	 */
	public	void	Update(TModel model);
	
	/**
	 * {@link Session#merge(Object)}
	 * @param model The model to merge.
	 */
	public	void	Merge(TModel model);
	
	/**
	 * {@link Session#delete(Object)}
	 * @param model The model to delete.
	 */
	public	void	Delete(TModel model);
	
	/**
	 * {@link Session#refresh(Object)}
	 * @param model The model to refresh.
	 */
	public	void	Refresh(TModel model);
	
	/**
	 * {@link Session#saveOrUpdate(Object)}
	 * @param model The model to saveOrUpdate.
	 */
	public	void	SaveOrUpdate(TModel model);
	
	/**
	 * @return the last inserted entity.
	 */
	public	TModel	GetLastInsertedEntity();
	
	/**
	 * @return the row count number.
	 */
	public	Number	GetRowCount();
}
