package com.sones.facebook.post.saver.wall.feed;

import com.sones.facebook.model.feed.comment.Comment;

public interface ICommentSaver 
{
	public	void	Save(Iterable<Comment> comments);
}
