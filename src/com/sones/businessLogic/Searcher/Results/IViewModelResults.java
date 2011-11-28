package com.sones.businessLogic.Searcher.Results;

import java.util.Iterator;

public interface IViewModelResults 
{
	public	Iterator<IResultViewModel> GetIterator();
	public	void	AddResult( IResultViewModel result);
	public int GetSize();
	public	IResultViewModel	GetResultAt(int index);
}
