package com.sones.businessLogic.DatabaseCleaner;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CleaningWindow {

	/**
	 * Distance of days
	 */
	private int daysDistance_;
	
	/**
	 * Distance of months
	 */
	private int monthsDistance_;
	
	/**
	 * The Calendar that keeps the date which has the distance from today
	 */
	private Calendar calendar_;
	
	/**
	 * 
	 * @param days
	 * @param months
	 */
	public CleaningWindow(int days,int months){
		this.setDaysDistance(days);
		this.setMonthsDistance(months);
		calendar_=new GregorianCalendar();
	}
	
	/**
	 * 
	 * @param daysDistance
	 */
	public void setDaysDistance(final int daysDistance){
		if(0<daysDistance){
			this.daysDistance_ = daysDistance;
		}
		else{
			this.daysDistance_=0;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int getDaysDistance(){
		return this.daysDistance_;
	}
	
	/**
	 * 
	 * @param monthsDistance
	 */
	public void setMonthsDistance(final int monthsDistance){
		if(0<monthsDistance){
			this.monthsDistance_=monthsDistance;
		}
		else{
			this.monthsDistance_=0;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int getMonthsDistance(){
		return monthsDistance_;
	}
	
	/**
	 * 
	 * @param calendar
	 * @return
	 */
	public int getMonth(Calendar calendar){
		int month=0;
		if(null!=calendar){
			month=(calendar.get(Calendar.MONTH))-this.getMonthsDistance();
			//System.out.println(calendar.get(Calendar.MONTH));

			if(0>month){
				month=11+month;
			}
			
		}
		else{
			System.out.println("Null calendar");
		}
		return month;
	}
	
	/**
	 * 
	 * @param calendar
	 * @return
	 */
	public int getDay(final Calendar calendar){
		int day=0;
		if(null!=calendar){
			day=(calendar.get(Calendar.DAY_OF_MONTH))-this.getDaysDistance();
			if(0==day){
				day = this.lastDayOfPreviousMonth(calendar);
			}
			else if(0>day){
				day=this.lastDayOfPreviousMonth()+day;
			}
			
		}
		else{
			System.out.println("Null calendar");
		}
		return day;
	}
	
	/**
	 * 
	 * @param calendar
	 * @return
	 */
	public int lastDayOfPreviousMonth(Calendar calendar){
		int day=0;
		if(((GregorianCalendar)calendar).isLeapYear(calendar.get(Calendar.YEAR))){
			if(calendar.get(Calendar.MONTH)==2){
				day=29;
			}
		}
		else{
			int month =calendar.get(Calendar.MONTH);
			if((month==1)||(month==3)||(month==7)||(month==8)||(month==10)||(month==0)){
				day=31;
			}
			else if(month==2){
				day=28;
			}
			else{
				day=30;
			}
		}
		return day;
	}

	/**
	 * 
	 * @return
	 */
	public int lastDayOfPreviousMonth(){
		Calendar calendar = new GregorianCalendar();
		return this.lastDayOfPreviousMonth(calendar);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDate(final Calendar now){
		
		//Calendar now = Calendar.getInstance();
		int date = this.getDay(now);
		int month= this.getMonth(now);
		int year = now.get(Calendar.YEAR);
		if(date>now.get(Calendar.DAY_OF_MONTH)){
			if(month==0){
				month=11;
				year--;
			}
		}
		calendar_.set(year, month, date);
		return this.formatDateToFacebookStyle(calendar_);
	}
	
	/**
	 * 
	 */
	public String getDate(){
		return this.getDate(Calendar.getInstance());
	}
	/**
	 * 
	 * @return
	 */
	public String getStartingDate(){
		return this.getDate(Calendar.getInstance());
	}

	public String getEndingDate(){
		return this.formatDateToFacebookStyle(Calendar.getInstance());
	}
	
	/**
	 * 
	 */
	public String formatDateToFacebookStyle(final Calendar calendar){
		String year=String.valueOf(calendar.get(Calendar.YEAR));
		int tmpMonth=calendar.get(Calendar.MONTH);
		String month;
		if((tmpMonth/10)==0){
			tmpMonth++;
			month="0"+tmpMonth;
		}
		else{
			tmpMonth++;
			month=String.valueOf(tmpMonth);
		}
		
		int tmpDay=calendar.get(Calendar.DAY_OF_MONTH);
		String day;
		if((tmpDay/10)==0){
			day="0"+tmpDay;
		}
		else{
			day=String.valueOf(tmpDay);
		}
		
		String date = year+"-"+month+"-"+day;
		return date;
	}
	
}
