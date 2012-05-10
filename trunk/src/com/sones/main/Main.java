package com.sones.main;

import java.util.HashSet;
import java.util.Set;

import com.sones.facebook.gui.downloader.DownloaderMainFrame;
import com.sones.facebook.gui.main.FacebookMainFrame;
import com.sones.facebook.gui.posts.CommentFrame;
import com.sones.facebook.gui.posts.LinkFrame;
import com.sones.facebook.gui.posts.PhotoFrame;
import com.sones.facebook.gui.searcher.SearcherMainFrame;
import com.sones.facebook.gui.sources.FacebookSourceSelectorFrame;
import com.sones.facebook.gui.user.FacebookTokenFrame;
import com.sones.facebook.gui.user.ILoginListener;
import com.sones.facebook.gui.user.LoginFrame;
import com.sones.sharedDto.facebook.view.posts.CommentViewDto;
import com.sones.sharedDto.facebook.view.posts.LinkViewDto;
import com.sones.sharedDto.facebook.view.posts.PhotoViewDto;
import com.sones.sharedDto.facebook.view.posts.UserViewDto;
import com.sones.sharedDto.usermanager.ApplicationUserViewDto;

public class Main implements ILoginListener
{
	public Main()
	{
		String[] args = new String[2];
		main(args);
	}
	
	/**
	 * @param args
	 */
	public void main(String[] args) 
	{
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.show();
		loginFrame.addListener(this);
	}
	
	private void showPhoto()
	{
		UserViewDto user = new UserViewDto();
		user.setUsername("Coca-Cola");
		
		PhotoViewDto photo = new PhotoViewDto();
		photo.setPicture("http://photos-e.ak.fbcdn.net/hphotos-ak-ash4/5041_98423808305_40796308305_1960517_6704612_s.jpg");
		photo.setName("A spectacular artwork made solely from used aluminum cans has been unveiled on top of the chalk cliffs of the Sussex coastline to mark the beginning of Recycle Week.");
		photo.setUser(user);
		
		PhotoFrame frame = new PhotoFrame();
		frame.setPost(photo);
		frame.show();
	}
	
	private void showLink()
	{
		UserViewDto user = new UserViewDto();
		user.setUsername("Stelios Stark Savramis");
		LinkViewDto link = new LinkViewDto();
		link.setLink("http://www.e-shop.gr/show_per.phtml?id=PER.907289");
		link.setDescription("SAMSUNG GALAXY TAB 10.1 P4 ANDROID 3.0 P7510 16GB WHITE");
		link.setPicture("https://s-external.ak.fbcdn.net/safe_image.php?d=AQD4FNKpI_0AS-zy&w=90&h=90&url=http\u00253A\u00252F\u00252Fimages.e-shop.gr\u00252Fimages\u00252FPER\u00252FPER.907289.jpg");
		link.setUser(user);
		
		LinkFrame frame = new LinkFrame();
		frame.setPost(link);
		frame.show();
	}
	
	private void showComment()
	{		
		Set<CommentViewDto> comments = new HashSet<CommentViewDto>();
		for(int index = 0; index < 10; index++)
		{
			UserViewDto user = new UserViewDto();
			user.setUsername("Sartios " + index);
			CommentViewDto comment = new CommentViewDto();
			comment.setUser(user);
			comment.setValue("Comment " + index);
			comments.add(comment);
		}
		CommentFrame commentFrame = new CommentFrame();
		commentFrame.setComments(comments);
		commentFrame.show();
	}

	@Override
	public void successfullLogin(ApplicationUserViewDto userDto)
	{		
		DownloaderMainFrame downloaderFrame = new DownloaderMainFrame();
		downloaderFrame.setUserDto(userDto);
		
		FacebookTokenFrame tokenFrame = new FacebookTokenFrame();
		tokenFrame.setUserDto(userDto);
		
		FacebookSourceSelectorFrame sourceFrame = new FacebookSourceSelectorFrame();
		sourceFrame.setUserDto(userDto);
		
		SearcherMainFrame searcherFrame = new SearcherMainFrame();
		searcherFrame.setUserDto(userDto);
		
		FacebookMainFrame facebookMainFrame = new FacebookMainFrame();
		facebookMainFrame.setSourceFrame(sourceFrame);
		facebookMainFrame.setDownloaderFrame(downloaderFrame);
		facebookMainFrame.setTokenFrame(tokenFrame);
		facebookMainFrame.setSearcherFrame(searcherFrame);
		facebookMainFrame.show();	
	}

}
