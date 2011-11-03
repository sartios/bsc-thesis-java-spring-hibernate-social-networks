package com.sones.collections;

import java.util.List;

public interface ICollection {
	public boolean hasNext();
	public void reset();
	public void increaseIndex();
	public Object getNext();
	public void addObject(final Object object);
	public boolean isEmpty();
	public List getAll();
}
