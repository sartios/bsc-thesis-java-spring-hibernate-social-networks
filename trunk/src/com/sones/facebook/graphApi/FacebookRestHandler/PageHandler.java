
package com.sones.facebook.graphApi.FacebookRestHandler;
import java.net.*;
import java.io.*;

import org.apache.log4j.Logger;

/**
 * Web page handler.
 * @author sartios.sones@gmail.com.
 *
 */
public class PageHandler {

	private	Logger	_logger;
	
	public	PageHandler()
	{
		_logger	=	Logger.getLogger( PageHandler.class );
	}
	
    /**
     * Connects to the url and get its contents
     * @param homeURL
     * @param content
     */
    public void getPage(String homeURL, StringBuffer content)
    {
    	try 
    	{     
    		URL	url	=	new	URL( homeURL );
	        HttpURLConnection	conn	=	( HttpURLConnection )url.openConnection();
	        conn.connect();
	        _logger.warn( " Opening connection with web " );

	        BufferedReader	rd	=	new	BufferedReader( new InputStreamReader( conn.getInputStream() ) );
	        String line;
	        
	        while ( ( line = rd.readLine() ) != null )
	        {
	        	_logger.warn( " Reading answer from web " );
	        	content.append(line);
	        }
	        _logger.warn(content);
	        conn.disconnect();
    	} 
    	catch (MalformedURLException ex) 
    	{
    		_logger.error( ex.getMessage() );

		}
    	catch (IOException ex) 
    	{
    		_logger.error( ex.getMessage() );
    		_logger.error( "Check Internet Connetion." );
		}
    	catch (Exception e) 
    	{
    		_logger.error( e.getMessage() );
    	}

	}

}
