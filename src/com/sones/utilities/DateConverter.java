package com.sones.utilities;

import java.text.ParseException;
import java.util.Calendar;
import java.util.TimeZone;

public class DateConverter {

	/**
	 * Returns a string to epoch - unixstamp format
	 * @param facebookDate
	 * @return unix time
	 */
	public String convertFacebookDateToUnixTimeStamp(String facebookDate){
		long epoch=0;
		if(null!=facebookDate){
			try {
				epoch = new java.text.SimpleDateFormat ("MM/dd/yyyy HH:mm:ss").parse(this.getReadableDateFromFacebookDate(facebookDate)).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(epoch);
	}
	
	/**
	 * Extracts from facebook date style the date and returns it like month/day/year hour:minute:second
	 * @param date
	 * @return date in month/day/year hour:minute:second format
	 */
	private String getReadableDateFromFacebookDate(String date){
		int year=Integer.parseInt(date.substring(0,4));
		int month=Integer.parseInt(date.substring(5,7));
		int day=Integer.parseInt(date.substring(8,10));
		int hour=Integer.parseInt(date.substring(11,13));
		int minute=Integer.parseInt(date.substring(14,16));
		int second=Integer.parseInt(date.substring(17,19));		
		String readableDate = month+"/"+day+"/"+year+" "+hour+":"+minute+":"+second;
		return readableDate;
	}
}
