package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public interface ICapitalWordsSearchableComments 
{
	public	List< String >	GetCapitalWords();
	public	Iterator<ICapitalWordsSearchableComment>	GetCommentIterator();
	public	void	AddComments(Collection<ICapitalWordsSearchableComment> comments);
	public void AddComment(ICapitalWordsSearchableComment comment);
}
