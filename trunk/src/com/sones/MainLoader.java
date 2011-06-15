package com.sones;

import com.sones.businessLogic.Facebook.FacebookSearchingList;
import com.sones.businessLogic.Facebook.FacebookSearchingManager;
import com.sones.businessLogic.SearchOrganizer.KeywordSearcher;
import com.sones.businessLogic.SearchOrganizer.SearchingTimer;
import com.sones.controllers.FeedViewerController;
import com.sones.controllers.FeedsWithCommentTrafficViewerController;
import com.sones.controllers.KeywordFeedViewerController;
import com.sones.controllers.KeywordMakerController;
import com.sones.controllers.SearcherController;
import com.sones.controllers.SourceSelectorController;
import com.sones.controllers.SourcesViewerController;
import com.sones.ui.KeywordFeedViewer;
import com.sones.ui.KeywordMaker;
import com.sones.ui.SearcherViewer;
import com.sones.ui.SourcesSelector_old;

public class MainLoader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*SourcesSelector selector = new SourcesSelector();
		FacebookSearchingList sources = new FacebookSearchingList();
		SourcesSelectorController controller = new SourcesSelectorController(selector, sources);
		selector.show();	*/
		
		//KeywordFeedViewer view_ = new KeywordFeedViewer();
		//KeywordFeedViewerController controller = new KeywordFeedViewerController(view_);
		
		//KeywordMaker view_ = new KeywordMaker();
		//KeywordMakerController controller = new KeywordMakerController(view_);
		//view_.show();
		
		SearcherController controller = new SearcherController(new SearcherViewer());
		controller.showForm();
		
		//FeedsWithCommentTrafficViewerController con = new FeedsWithCommentTrafficViewerController();
		//con.showForm();
		
		//SourceSelectorController controller = new SourceSelectorController();
		//controller.showForm();
		
		//SourcesViewerController controller = new SourcesViewerController();
		//FacebookSearchingList sources = new FacebookSearchingList();
		//sources.addID("117980814922413");
		//sources.addID("100000109606136");
		//controller.setSources(sources);
		//controller.showForm();
		
	
		//FacebookSearchingManager manager = new FacebookSearchingManager();
		//FacebookSearchingList sources = new FacebookSearchingList();
		//sources.addID("502894293");
		//sources.addID("100000866964787");
		//manager.setSources(sources);
		//manager.setSearcher(new KeywordSearcher());
		
		//SearchingTimer timer = new SearchingTimer(10000);
		//timer.setFacebookSearchingManager(manager);
		//timer.start();	
	}

}
