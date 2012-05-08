package com.sones.facebook.controller.posts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.sones.sharedDto.facebook.view.posts.CommentViewDto;

public class CommentController
{
	private List<CommentViewDto> commentDtos;
	private int index;
	
	public CommentController()
	{
		commentDtos = new ArrayList<CommentViewDto>();
		index = -1;
	}
	
	public CommentViewDto getNext()
	{
		index++;
		return commentDtos.get(index);
	}
	
	public CommentViewDto getPrevious()
	{
		index--;
		return commentDtos.get(index);
	}
	
	public boolean hasNext()
	{
		if(index < commentDtos.size())
		{
			return true;
		}
		return false;
	}
	
	public boolean hasPrevious()
	{
		try{
			int prev = index;
			prev--;
			commentDtos.get(prev);
		}
		catch(IndexOutOfBoundsException ex){
			return false;
		}
		return true;
	}
	
	public void addComments(Iterable<CommentViewDto> comments)
	{
		System.out.println("Add comments");
		for(CommentViewDto comment : comments)
		{
			commentDtos.add(comment);
		}
	}
}
