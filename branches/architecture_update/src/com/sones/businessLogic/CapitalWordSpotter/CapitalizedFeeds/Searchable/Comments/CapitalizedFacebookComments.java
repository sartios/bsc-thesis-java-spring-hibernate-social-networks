package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;

public class CapitalizedFacebookComments	implements	ICapitalWordsSearchableComments
{
	private	List<ICapitalWordsSearchableComment>	_comments;

	public	CapitalizedFacebookComments()
	{
		_comments	=	new	ArrayList<ICapitalWordsSearchableComment>();
	}
	
	@Override
	public List<String> GetCapitalWords() 
	{
		throw	new	NotImplementedException("GetCapitalWords() is not implemented.");
	}

	@Override
	public Iterator<ICapitalWordsSearchableComment> GetCommentIterator()
	{
		return	_comments.iterator();
	}

	@Override
	public void AddComments( Collection<ICapitalWordsSearchableComment> comments ) 
	{
		_comments.addAll(comments);
	}

	@Override
	public void AddComment(ICapitalWordsSearchableComment comment) 
	{
		_comments.add(comment);
	}

}
