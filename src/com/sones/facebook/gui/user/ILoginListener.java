package com.sones.facebook.gui.user;

import com.sones.sharedDto.usermanager.ApplicationUserViewDto;

public interface ILoginListener 
{
	public void successfullLogin(ApplicationUserViewDto userDto);
}
