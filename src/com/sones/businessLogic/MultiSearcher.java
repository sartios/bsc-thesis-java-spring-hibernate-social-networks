package com.sones.businessLogic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MultiSearcher extends AbstractSearcher{

	private List<AbstractSearcher> searchers;
	private Set<Feed> feedsAsResult=null;
	
	public MultiSearcher(){
		searchers = new ArrayList<AbstractSearcher>();
		feedsAsResult = new HashSet<Feed>();
	}
	
	public MultiSearcher(List<AbstractSearcher> listOfSearchers){
		feedsAsResult = new HashSet<Feed>();
		searchers = new ArrayList<AbstractSearcher>();
		for(int i=0;i<listOfSearchers.size();i++){
			searchers.add(listOfSearchers.get(i));
		}
	}
	
	public boolean addSearcher(AbstractSearcher searcher){
		return searchers.add(searcher);
	}
	
	@Override
	public void search() {
		if(null!=super.getFeeds()){
			for(int i=0;i<searchers.size();i++){
				searchers.get(i).setFeeds(super.getFeeds());
				searchers.get(i).search();
			}
		}
	}
}
