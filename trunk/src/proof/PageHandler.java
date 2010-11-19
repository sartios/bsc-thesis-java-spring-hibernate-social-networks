

package proof;
import java.net.*;
import java.io.*;

public class PageHandler {
    
    public static void getPage(String homeURL, StringBuffer content){

        try {     
	        URL url = new URL(homeURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	              conn.connect();

	        
	        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line;
                
	        while ((line = rd.readLine()) != null) {
	           content.append(line);
                                  
	    }
	           conn.disconnect();

	  } catch (Exception e) {    }

	}

}
