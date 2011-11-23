package utilities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.utilities.DateConverter;

import static org.junit.Assert.*;

public class DateConverterTest {
	private DateConverter converter;
	
	@Before
	public void setUp(){
		converter = new  DateConverter();
	}
	
	@After
	public void tearDown(){
		converter=null;
	}
	
	@Test
	/**
	 * The date is null
	 */
	public void getUnixTimestamp_NullAsDate_Test(){
		String date=null;
		assertEquals(converter.getUnixTimestamp(date), 0);
	}
	
	@Test
	/**
	 * The date we pass isn't a facebook date
	 */
	public void getUnixTimestamp_NotFacebookDate_Test(){
		String date="16/06/2011 23:45:38";
		assertEquals(converter.getUnixTimestamp(date), 0);
	}
	
	@Test
	/**
	 * We give a facebook date and must return true
	 */
	public void dateIsInFacebookFormat_facebookDate_Test(){
		String date="2011-06-16T12:34:44+0000";
		assertTrue(converter.dateIsInFacebookFormat(date));
	}
}
