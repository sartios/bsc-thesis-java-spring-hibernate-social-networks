package com.sones.businessLogic.Facebook.Feeds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.FacebookFeedList;
import com.sones.businessLogic.Facebook.FeedListUsingList;

public class QuickSortFeedListSorter implements IFeedListSorter{
	
	@Override
	public List getSortedFeedListNewestFirst(final List<Feed> feeds) {
		List<Feed> sortedFeedList = new ArrayList<Feed>();
		
		int size = feeds.size();
		int lowerIndex = 0;
		int higherIndex = size-1;
		
		sortedFeedList = feeds;
		this.quickSortList(sortedFeedList, lowerIndex, higherIndex);
		return sortedFeedList;	
	}

	@Override
	public List getSortedFeedListOldestFirst(final List<Feed> feeds) {
		List<Feed> sortedFeedList =  new ArrayList<Feed>();
		
		int size = feeds.size();
		int lowerIndex = 0;
		int higherIndex = size-1;
		
		sortedFeedList = feeds;
		this.quickSortList(sortedFeedList, lowerIndex, higherIndex);
		return sortedFeedList;
	}
	
	public void quickSortList(List<Feed> feeds,int left,int right){
		
		int i;
		int j;
		int mid;
		Feed tmp;
		long creationTimeOfMiddleFeed;
		
		if(left<right){
			i=left;
			j=right;
			mid=(left+right)/2;
			creationTimeOfMiddleFeed=this.getCreationTime(feeds.get(mid));
			//System.out.println("i= "+i);
			//System.out.println("j= "+j);
			while(i<j){
				while(getCreationTime(feeds.get(i))>creationTimeOfMiddleFeed){
					i++;
				//	System.out.println("i= "+i);
				}
				while(getCreationTime(feeds.get(j))<creationTimeOfMiddleFeed){
					j--;
					//System.out.println("j= "+j);

				}
				if(i<j){//System.out.println("i<j ->"+i+"<"+j);

					if(getCreationTime(feeds.get(i))==getCreationTime(feeds.get(j))){
						if(i<mid){
							i++;
						}
						if(j>mid){
							j--;
						}
					}
					else{
						tmp=feeds.get(i);
						feeds.set(i, feeds.get(j));
						feeds.set(j, tmp);
					}
				}
				
			}
			quickSortList(feeds, left, j-1);
			quickSortList(feeds, j+1, right);
		}	
		
		System.out.println("Newest feed= "+feeds.get(0).getCreatedTime_());
		System.out.println("Oldest feed= "+feeds.get(feeds.size()-1).getCreatedTime_());
	}
	

	private boolean feedListIsValid(final FeedListUsingList feeds){
		if(null!=feeds){
			if(feeds.isEmpty()==false){
				return true;
			}
		}
		return false;
	}
	
	private long getCreationTime(final Feed feed){
		long creationTime= Long.parseLong(feed.getCreatedTime_());
		return creationTime;
	}

	

}
