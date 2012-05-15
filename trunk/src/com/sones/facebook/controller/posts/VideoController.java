package com.sones.facebook.controller.posts;

import java.io.IOException;

public class VideoController 
{
	public void goToVideo(String url)
	{
		if(url != null)
		{
			try
			{
				Process p=Runtime.getRuntime().exec("cmd /c start "+url);
			}
			catch(IOException e1)
			{
				System.out.println(e1);
			}
		}
	}
}
