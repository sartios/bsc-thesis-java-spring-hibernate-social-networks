package com.sones.businessLogic.Facebook.Source;

public interface IFacebookSourceCollection {

	public ISource getNext();
	public boolean hasNext();
	public void addSource(final ISource source);
	public void reset();
}
