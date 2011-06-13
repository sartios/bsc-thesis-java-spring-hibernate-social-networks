package com.sones.controllers;

import com.sones.businessLogic.Feed;

public interface DisplayableFeedController {
	public void setFeed(final Feed status);
	public void showFeed();
}
