package com.sones.controllers.results.calendar;

public interface ICalendarController 
{
	public	void	SetFromDate( String date );
	public	String	GetFromDate();
	
	public	void	SetEndDate( String date );
	public	String	GetEndDate();
	
	public	void	ShowCalendarDialog();
}
