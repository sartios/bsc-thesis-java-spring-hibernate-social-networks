package com.sones.facebook.dao.feed.comment;

import com.sones.dao.IGenericDao;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.feed.comment.Comment;

public interface ICommentDao extends IGenericDao<Comment, String>
{
	public Iterable<Comment> getByFacebookPost( FacebookPost post );
}
