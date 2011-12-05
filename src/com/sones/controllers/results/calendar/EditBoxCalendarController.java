package com.sones.controllers.results.calendar;

import javax.swing.JFrame;

import com.sones.ui.results.calendar.EditBoxCalendar;

public class EditBoxCalendarController	implements	ICalendarController
{
	
	private	String 	_fromDate;
	private	String	_endDate;
	private	EditBoxCalendar	_view;
	
	public	EditBoxCalendarController(JFrame owner)
	{
		_view	=	new	EditBoxCalendar(owner,this);
	}

	@Override
	public String GetEndDate()
	{
		return _endDate;
	}

	@Override
	public String GetFromDate() 
	{
		return _fromDate;
	}

	@Override
	public void SetEndDate(String date)
	{
		_endDate	=	date;
	}

	@Override
	public void SetFromDate(String date)
	{
		_fromDate	=	date;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public	void	ShowCalendarDialog()
	{
		_view.show(true);
	}
}
