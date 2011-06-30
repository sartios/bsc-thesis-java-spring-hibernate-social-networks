package com.sones.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DateConverter {

	public String convertFacebookDateToUnixTimeStamp(String facebookDate){
		long epoch=0;
		if(null!=facebookDate){
			try {
				epoch = new java.text.SimpleDateFormat ("MM/dd/yyyy HH:mm:ss").parse(this.getReadableDate(facebookDate)).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(epoch);
	}
	
	private String getReadableDate(String date){
		int year=Integer.parseInt(date.substring(0,4));
		int month=Integer.parseInt(date.substring(5,7));
		int day=Integer.parseInt(date.substring(8,10));
		int hour=Integer.parseInt(date.substring(11,13));
		int minute=Integer.parseInt(date.substring(14,16));
		int second=Integer.parseInt(date.substring(17,19));		
		String readableDate = month+"/"+day+"/"+year+" "+hour+":"+minute+":"+second;
		
		return readableDate;
	}
	
	public long getUnixTimestamp(String date){		
		
		String epoch=new String();
		
		if(dateIsInFacebookFormat(date)){
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC+00:00"));
			int year=Integer.parseInt(date.substring(0,4));
		
			// Month starts from 0
			int month=Integer.parseInt(date.substring(5,7))-1;
		
			int day=Integer.parseInt(date.substring(8,10));
			int hour=Integer.parseInt(date.substring(11,13));
			int minute=Integer.parseInt(date.substring(14,16));
			int second=Integer.parseInt(date.substring(17,19));		

			calendar.set(year, month, day, hour, minute, second);
			epoch = String.valueOf((calendar.getTime()).getTime()).substring(0, 10);
		}
		else{
			epoch="0";
		}
		
		return Long.parseLong(epoch);
	}
	
	public boolean dateIsInFacebookFormat(final String date){
		boolean isInFacebookFormat=false;
		if(null!=date){
			String tmpDate = date.replace("T", "");
			String facebookDateFormat = new String("yyyy-mm-ddhh:mm:ss+0000");
			try{
				SimpleDateFormat dateFormat = new SimpleDateFormat(facebookDateFormat);
				dateFormat.parse(tmpDate);
				isInFacebookFormat=true;
			}
			catch (ParseException e) {
				isInFacebookFormat=false;
			}
		}
		return isInFacebookFormat;
	}
}
