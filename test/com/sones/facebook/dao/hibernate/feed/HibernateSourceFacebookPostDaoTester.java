package com.sones.facebook.dao.hibernate.feed;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.dao.IGenericDao;
import com.sones.facebook.dao.feed.IFacebookPostDao;
import com.sones.facebook.dao.feed.ISourceFacebookPostDao;
import com.sones.facebook.dao.hibernate.HibernateDaoTesterUtil;
import com.sones.facebook.dao.hibernate.source.HibernateSourceDao;
import com.sones.facebook.dao.hibernate.source.HibernateSourceTypeDao;
import com.sones.facebook.dao.hibernate.source.HibernateUserDao;
import com.sones.facebook.dao.source.ISourceDao;
import com.sones.facebook.dao.source.ISourceTypeDao;
import com.sones.facebook.dao.source.IUserDao;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.feed.SourceFacebookPost;
import com.sones.facebook.model.source.Source;
import com.sones.facebook.model.source.SourceType;
import com.sones.facebook.model.source.User;

public class HibernateSourceFacebookPostDaoTester extends HibernateDaoTesterUtil
{
	private SourceFacebookPost sourceFacebookPost;
	private ISourceFacebookPostDao sourceFacebookPostDao;
	private IUserDao userDao;
	private ISourceDao sourceDao;
	private IFacebookPostDao facebookPostDao;
	private ISourceTypeDao sourceTypeDao;
	private Source source;
	private SourceType sourceType;
	private User user;
	private FacebookPost post;	
	
	public HibernateSourceFacebookPostDaoTester()
	{
		super();
	}
	
	@Before
	public void SetUp()
	{
		sourceFacebookPost = (SourceFacebookPost) getModelContext().getBean("sourceFacebookPost");
		sourceFacebookPostDao = (HibernateSourceFacebookPostDao) getDAOContext().getBean("sourceFacebookPostDao");
		userDao = (HibernateUserDao) getDAOContext().getBean("userDao");
		sourceDao = (HibernateSourceDao) getDAOContext().getBean("sourceDao");
		facebookPostDao = (HibernateFacebookPostDao) getDAOContext().getBean("facebookPostDao");
		sourceTypeDao = (HibernateSourceTypeDao) getDAOContext().getBean("sourceTypeDao");
		
		source = sourceFacebookPost.getId().getSource();
		sourceType = source.getType();
		user = sourceFacebookPost.getId().getPost().getUser();
		post = sourceFacebookPost.getId().getPost();
		post.setComments( null );
		
		saveIfNotExist(sourceType, sourceType.getId(), sourceTypeDao);	
		saveIfNotExist(source, source.getId(), sourceDao);
		saveIfNotExist(user,user.getId(),userDao);
		saveIfNotExist(post, post.getId(), facebookPostDao);
		saveIfNotExist(sourceFacebookPost, sourceFacebookPost.getId(), sourceFacebookPostDao);		
	}
	
	@After
	public void TearDown()
	{
		deleteExists(sourceFacebookPost, sourceFacebookPost.getId(), sourceFacebookPostDao);		
		deleteExists(source, source.getId(), sourceDao);		
		deleteExists(post, post.getId(), facebookPostDao);
		deleteExists(sourceType, sourceType.getId(), sourceTypeDao);		
		deleteExists(user,user.getId(),userDao);
	}
	
	@Test
	public void TestSaveSourceFacebookPostSource()
	{
		SourceFacebookPost dpPost = sourceFacebookPostDao.GetById(sourceFacebookPost.getId());
		assertEquals(sourceFacebookPost.getId().getSource(), dpPost.getId().getSource());
	}
	
	@Test
	public void TestSaveSourceFacebookPostFacebookPost()
	{
		SourceFacebookPost dpPost = sourceFacebookPostDao.GetById(sourceFacebookPost.getId());
		assertEquals(sourceFacebookPost.getId().getPost(), dpPost.getId().getPost());
	}
	
	@Test
	public	void	TestGetSourceFacebookPostsBySourceAndAfterDate()
	{
		fail( "Changes to model need to be made" );
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void TestGetBySourceTypeWithNullType()
	{
		sourceFacebookPostDao.GetBySourceType( null );
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void TestGetBySourceTypeWithEmptyType()
	{
		SourceType emptyType = new SourceType();
		sourceFacebookPostDao.GetBySourceType( emptyType );
	}
	
	@Test
	public void TestGetBySourceType()
	{
		Iterable<SourceFacebookPost> models = getSourceFacebookPostWithVariousSourceTypes();
		SourceFacebookPost model = models.iterator().next();
		Source modelSource = model.getId().getSource();
		SourceType modelType = modelSource.getType();
		List<SourceFacebookPost> results = (List<SourceFacebookPost>) sourceFacebookPostDao.GetBySourceType( modelType );
		assertEquals(10,results.size());
		
		deleteIfExists(models);

	}
	
	@Test
	public void TestGetBySources()
	{
		Iterable<SourceFacebookPost> models = getSourceFacebookPostWithVariousSourceTypes();
		SourceFacebookPost model = models.iterator().next();
		Source modelSource = model.getId().getSource();
		SourceType modelType = modelSource.getType();
		List< Source > sources = new ArrayList<Source>();
		sources.add( modelSource );
		List<SourceFacebookPost> results = (List<SourceFacebookPost>) sourceFacebookPostDao.GetBySources( sources );
		assertEquals(10,results.size());
		
		deleteIfExists(models);

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void TestGetInSourcesAndInPostsWithNullSources()
	{
		sourceFacebookPostDao.GetInSourcesAndInPosts(null, new ArrayList<FacebookPost>());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void TestGetInSourcesAndInPostsWithNullPosts()
	{
		sourceFacebookPostDao.GetInSourcesAndInPosts(new ArrayList<Source>(), null);
	}
	
	@Test
	public void TestGetInSourcesAndInPosts()
	{
		Iterable<SourceFacebookPost> models = getSourceFacebookPostWithVariousSourceTypes();
		SourceFacebookPost model = models.iterator().next();
		Source modelSource = model.getId().getSource();
		SourceType modelType = modelSource.getType();
		List< Source > sources = new ArrayList<Source>();
		List< FacebookPost > posts = new ArrayList<FacebookPost>();
		sources.add( modelSource );
		int count = 0;
		for( SourceFacebookPost sfpost : models )
		{
			count++;
			posts.add( sfpost.getId().getPost() );
			if(count == 6 )
			{
				break;
			}
		}
		List<SourceFacebookPost> results = (List<SourceFacebookPost>) sourceFacebookPostDao.GetInSourcesAndInPosts(sources, posts);
		assertEquals(6,results.size());
		
		deleteIfExists(models);
	}
	
	private void deleteIfExists(Iterable<SourceFacebookPost> models )
	{
		Source fsource = null;
		for( SourceFacebookPost model : models )
		{
			if( fsource == null )
			{
				fsource = model.getId().getSource();
			}
			deleteExists(model, model.getId(), sourceFacebookPostDao);
			deleteExists(model.getId().getPost(), model.getId().getPost().getId(), facebookPostDao);
		}
		deleteExists(fsource, fsource.getId(), sourceDao);
		deleteExists(fsource.getType(), fsource.getType().getId(), sourceTypeDao);

	}
	
	private Iterable<SourceFacebookPost> getSourceFacebookPostWithVariousSourceTypes()
	{
		Iterable< FacebookPost > posts = getFacebookPosts();
		posts = saveFacebookPostAndReturn( posts );
		Source fsource = getSource("Place");
		return getSourceFacebookPosts(posts, fsource);
	}
	
	private Source getSource( String typeValue )
	{
		SourceType stype = new SourceType(typeValue);
		Number num = sourceTypeDao.GetRowCount();
		String typeId = new String( String.valueOf( num.intValue() + 5 ) );
		stype.setId( typeId );
		saveIfNotExist(stype,stype.getId(),sourceTypeDao);
		
		Source fsource = new Source();
		fsource.setType(stype);
		Number num2 = sourceDao.GetRowCount();
		String sourceId = new String( String.valueOf( num2.intValue() + 5 ) );
		fsource.setId( sourceId );
		saveIfNotExist(fsource,fsource.getId(),sourceDao);
		return fsource;
	}
	
	private Iterable<SourceFacebookPost> getSourceFacebookPosts( Iterable< FacebookPost > posts, Source fsource )
	{
		List<SourceFacebookPost> sourceFacebookPosts = new ArrayList<SourceFacebookPost>();
		for( FacebookPost fpost : posts )
		{
			SourceFacebookPost elem = new SourceFacebookPost(fpost,fsource);
			sourceFacebookPosts.add(elem);
			saveIfNotExist(elem, elem.getId(), sourceFacebookPostDao);
		}
		return sourceFacebookPosts;
	}
	
	private Iterable< FacebookPost > getFacebookPosts()
	{
		List<FacebookPost> posts = new ArrayList<FacebookPost>();
		for( int postIndex = 1; postIndex < 11; postIndex++ )
		{
			FacebookPost fPost = new FacebookPost();
			fPost.setId( String.valueOf( postIndex + 5 ) );
			posts.add( fPost );
		}
		return posts;
	}
	
	private Iterable< FacebookPost > saveFacebookPostAndReturn( Iterable< FacebookPost > posts )
	{
		for( FacebookPost fpost : posts )
		{
			saveIfNotExist(fpost, fpost.getId(), facebookPostDao);
		}
		return posts;
	}
	
	private void saveIfNotExist( Object model , Object id, IGenericDao dao )
	{
		if( dao.GetById( id ) == null )
		{
			dao.Save( model );
		}
	}
	
	private void deleteExists( Object model , Object id, IGenericDao dao )
	{
		if( dao.GetById( id ) != null )
		{
			dao.Delete( model );
		}
	}
}
