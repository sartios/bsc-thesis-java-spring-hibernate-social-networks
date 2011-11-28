package com.sones.businessLogic.Searcher.Results;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class ViewModelResults	implements	IViewModelResults
{
	private	List<IResultViewModel>	_results;
	private	Map<String, Integer>	_index;
	private	Logger	_logger;

	public	ViewModelResults()
	{
		_results	=	new	ArrayList<IResultViewModel>();
		_index	=	new	HashMap<String, Integer>();
		_logger	=	Logger.getLogger(ViewModelResults.class);
	}
	
	@Override
	public void AddResult(IResultViewModel result) 
	{
		if( true == _index.containsKey(result.GetFeedID()) )
		{
			_logger.warn("Duplicate key");
			int	index	=	_index.get(result.GetFeedID());
			IResultViewModel	prevResult	=	_results.get(index);
			_results.remove(index);
			prevResult.AddKeyword(result.GetKeywords());
			_results.add(index, prevResult);
		}
		else	if( false == _index.containsKey(result.GetFeedID()) )
		{
			_logger.warn("Key does not exist");
			int	index	=	_results.size();
			_results.add(index,result);
			_index.put(result.GetFeedID(), index);
		}
	}

	@Override
	public Iterator<IResultViewModel> GetIterator() 
	{
		return	_results.iterator();
	}

	@Override
	public int GetSize()
	{
		return	_results.size();
	}

	@Override
	public IResultViewModel GetResultAt(int index) 
	{
		return	_results.get(index);
	}


}
