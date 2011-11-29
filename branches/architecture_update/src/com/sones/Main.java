package com.sones;

import com.sones.ui.main.SonesConsole;
import com.sones.ui.results.LinkView;
import com.sones.ui.results.PictureView;
import com.sones.ui.results.ResultsView;
import com.sones.ui.results.StatusMessageView;

public class Main {

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) 
	{
		Main.RunSonesConsole();
	}
	
	private static	void	RunResultView()
	{
		ResultsView	results	=	new	ResultsView();
		results.show(true);
	}
	
	private static	void	RunLink()
	{
		LinkView	link	=	new	LinkView();
		link.SetFeedID("551366621_238053209587858");
		link.show(true);
	}
	
	private	static	void	RunPicture()
	{
		PictureView	picture	=	new	PictureView();
		picture.SetFeedID("545564400_10150357992134857");
		picture.show(true);
	}

	private	static	void	RunStatusMessage()
	{
		StatusMessageView	status	=	new	StatusMessageView();
		status.SetFeedID("545564400_10150337654104401");
		status.show(true);
	}
	
	private	static	void	RunSonesConsole()
	{
		SonesConsole	console	=	new	SonesConsole();
		console.show(true);
	}
}
