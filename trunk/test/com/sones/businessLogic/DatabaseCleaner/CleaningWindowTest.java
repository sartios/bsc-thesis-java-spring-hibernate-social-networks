package com.sones.businessLogic.DatabaseCleaner;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CleaningWindowTest {

	@Test
	public void getMonth_MonthIsMoreThanZero_Test(){
		CleaningWindow window = new CleaningWindow(0, 5);
		Calendar calendar = Calendar.getInstance();
		assertEquals(window.getMonth(calendar), 0);
	}
	
	@Test
	public void getMonth_MonthIsBiggerThanTheCurrent_Test(){
		Calendar calendar = Calendar.getInstance();
		CleaningWindow window = new CleaningWindow(0, 10);
		assertEquals(window.getMonth(calendar), 6);
	}
	
	@Test
	public void getMonth_MonthIsCurrent_Test(){
		Calendar calendar = Calendar.getInstance();
		CleaningWindow window = new CleaningWindow(0, calendar.get(Calendar.MONTH)+1);
		System.out.println(calendar.get(Calendar.MONTH)+1);
		assertEquals(window.getMonth(calendar), 10);
	}
	
	@Test
	public void getMonth_MonthIsZero_Test(){
		Calendar calendar = Calendar.getInstance();
		CleaningWindow window = new CleaningWindow(0, 0);
		assertEquals(window.getMonth(calendar), 5);
	}
	
	@Test
	public void getMonth_CalendarIsNull_Test(){
		CleaningWindow window = new CleaningWindow(0, 9);
		assertEquals(window.getMonth(null),0);
	}
	
	@Test
	public void lastDayOfPreviousMonth_LastMonthIsJanuary_Test(){
		CleaningWindow window = new CleaningWindow(0, 9);
		GregorianCalendar calendar = new GregorianCalendar(2011, 1, 10);
		assertEquals(window.lastDayOfPreviousMonth(calendar), 31);
	}
	
	@Test
	public void lastDayOfPreviousMonth_LastMonthIsFebruaryNotLeapYear_Test(){
		CleaningWindow window = new CleaningWindow(0, 9);
		GregorianCalendar calendar = new GregorianCalendar(2011, 2, 10);
		assertEquals(window.lastDayOfPreviousMonth(calendar), 28);
	}
	
	@Test
	public void lastDayOfPreviousMonth_LastMonthIsMarch_Test(){
		CleaningWindow window = new CleaningWindow(0, 9);
		GregorianCalendar calendar = new GregorianCalendar(2011, 3, 10);
		assertEquals(window.lastDayOfPreviousMonth(calendar), 31);
	}
	
	@Test
	public void lastDayOfPreviousMonth_LastMonthIsApril_Test(){
		CleaningWindow window = new CleaningWindow(0, 9);
		GregorianCalendar calendar = new GregorianCalendar(2011, 4, 10);
		assertEquals(window.lastDayOfPreviousMonth(calendar), 30);
	}
	
	@Test
	public void lastDayOfPreviousMonth_LastMonthIsMay_Test(){
		CleaningWindow window = new CleaningWindow(0, 9);
		GregorianCalendar calendar = new GregorianCalendar(2011, 5, 10);
		assertEquals(window.lastDayOfPreviousMonth(calendar), 30);
	}
	
	@Test
	public void lastDayOfPreviousMonth_LastMonthIsJune_Test(){
		CleaningWindow window = new CleaningWindow(0, 9);
		GregorianCalendar calendar = new GregorianCalendar(2011, 6, 10);
		assertEquals(window.lastDayOfPreviousMonth(calendar), 30);
	}
	
	@Test
	public void lastDayOfPreviousMonth_LastMonthIsJuly_Test(){
		CleaningWindow window = new CleaningWindow(0, 9);
		GregorianCalendar calendar = new GregorianCalendar(2011, 7, 10);
		assertEquals(window.lastDayOfPreviousMonth(calendar), 31);
	}
	
	@Test
	public void lastDayOfPreviousMonth_LastMonthIsAugust_Test(){
		CleaningWindow window = new CleaningWindow(0, 9);
		GregorianCalendar calendar = new GregorianCalendar(2011, 8, 10);
		assertEquals(window.lastDayOfPreviousMonth(calendar), 31);
	}
	
	@Test
	public void lastDayOfPreviousMonth_LastMonthIsSeptember_Test(){
		CleaningWindow window = new CleaningWindow(0, 9);
		GregorianCalendar calendar = new GregorianCalendar(2011, 9, 10);
		assertEquals(window.lastDayOfPreviousMonth(calendar), 30);
	}
	
	@Test
	public void lastDayOfPreviousMonth_LastMonthIsOctober_Test(){
		CleaningWindow window = new CleaningWindow(0, 9);
		GregorianCalendar calendar = new GregorianCalendar(2011, 10, 10);
		assertEquals(window.lastDayOfPreviousMonth(calendar), 31);
	}
	
	@Test
	public void lastDayOfPreviousMonth_LastMonthIsNovember_Test(){
		CleaningWindow window = new CleaningWindow(0, 9);
		GregorianCalendar calendar = new GregorianCalendar(2011, 11, 10);
		assertEquals(window.lastDayOfPreviousMonth(calendar), 30);
	}
	
	@Test
	public void lastDayOfPreviousMonth_LastMonthIsDecember_Test(){
		CleaningWindow window = new CleaningWindow(0, 9);
		GregorianCalendar calendar = new GregorianCalendar(2011, 0, 10);
		assertEquals(window.lastDayOfPreviousMonth(calendar), 31);
	}
	
	@Test
	public void getDay_31thOfJanuary_Test(){
		CleaningWindow window = new CleaningWindow(1, 0);
		GregorianCalendar calendar = new GregorianCalendar(2011, 1, 1);
		assertEquals(window.getDay(calendar), 31);
	}
	
	@Test
	public void getDay_28thOfFebruary_Test(){
		CleaningWindow window = new CleaningWindow(1, 0);
		GregorianCalendar calendar = new GregorianCalendar(2011, 2, 1);
		assertEquals(window.getDay(calendar), 28);
	}
	
	@Test
	public void getDay_30thOfMarch_Test(){
		CleaningWindow window = new CleaningWindow(1, 0);
		GregorianCalendar calendar = new GregorianCalendar(2011, 3, 1);
		assertEquals(window.getDay(calendar), 31);
	}
	
	@Test
	public void getDay_30thOfApril_Test(){
		CleaningWindow window = new CleaningWindow(1, 0);
		GregorianCalendar calendar = new GregorianCalendar(2011, 4, 1);
		assertEquals(window.getDay(calendar), 30);
	}
	
	@Test
	public void getDay_30thOfMay_Test(){
		CleaningWindow window = new CleaningWindow(1, 0);
		GregorianCalendar calendar = new GregorianCalendar(2011, 5, 1);
		assertEquals(window.getDay(calendar), 30);
	}
	
	@Test
	public void getDay_30thOfJune_Test(){
		CleaningWindow window = new CleaningWindow(1, 0);
		GregorianCalendar calendar = new GregorianCalendar(2011, 6, 1);
		assertEquals(window.getDay(calendar), 30);
	}
	
	@Test
	public void getDay_31thOfJuly_Test(){
		CleaningWindow window = new CleaningWindow(1, 0);
		GregorianCalendar calendar = new GregorianCalendar(2011, 7, 1);
		assertEquals(window.getDay(calendar), 31);
	}
	
	@Test
	public void getDay_31thOfAugust_Test(){
		CleaningWindow window = new CleaningWindow(1, 0);
		GregorianCalendar calendar = new GregorianCalendar(2011, 8, 1);
		assertEquals(window.getDay(calendar), 31);
	}
	
	@Test
	public void getDay_30thOfSeptember_Test(){
		CleaningWindow window = new CleaningWindow(1, 0);
		GregorianCalendar calendar = new GregorianCalendar(2011, 9, 1);
		assertEquals(window.getDay(calendar), 30);
	}
	
	@Test
	public void getDay_31thOfOctober_Test(){
		CleaningWindow window = new CleaningWindow(1, 0);
		GregorianCalendar calendar = new GregorianCalendar(2011, 10, 1);
		assertEquals(window.getDay(calendar), 31);
	}
	
	@Test
	public void getDay_30thOfNovember_Test(){
		CleaningWindow window = new CleaningWindow(1, 0);
		GregorianCalendar calendar = new GregorianCalendar(2011, 11, 1);
		assertEquals(window.getDay(calendar), 30);
	}
	
	@Test
	public void getDay_31thOfDecember_Test(){
		CleaningWindow window = new CleaningWindow(1, 0);
		GregorianCalendar calendar = new GregorianCalendar(2011, 0, 1);
		assertEquals(window.getDay(calendar), 31);
	}
	
	@Test
	public void getDate_2DaysAgo_Test(){
		CleaningWindow window = new CleaningWindow(2, 0);
		assertEquals(window.getDate(),"2011-06-20");
		
	}
	
	@Test
	public void getDate_LastYear_Test(){
		CleaningWindow window = new CleaningWindow(2, 0);
		Calendar calendar = new GregorianCalendar(2011, 0, 1);
		assertEquals(window.getDate(calendar),"2010-12-29");
	}
	
	@Test
	public void formatDateToFacebookStyle_ADate_Test(){
		CleaningWindow window = new CleaningWindow(2, 0);
		Calendar calendar = new GregorianCalendar(2011, 0, 1);
		assertEquals(window.formatDateToFacebookStyle(calendar),"2011-01-01");
	}
}
