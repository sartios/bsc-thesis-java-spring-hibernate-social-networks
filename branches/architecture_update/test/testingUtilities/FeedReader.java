package testingUtilities;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class FeedReader {

	
	public String getFile(final String filename){
		String file=new String();
		try{
			FileInputStream fstream=new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while((strLine=br.readLine())!=null){
				file=file+"\n"+strLine;
			}
			in.close();
		}
		catch (Exception e) {
			 System.err.println("Error: " + e.getMessage());		
		}
		return file;
	}
	
	public String getFacebookStatusMessage(){
		return this.getFile("JSON Documents\\StatusMessage.txt");
	}
	
	public String getFacebookVideo(){
		return this.getFile("JSON Documents\\Video.txt");
	}
	
	public String getFacebookPhoto(){
		return this.getFile("JSON Documents\\Photo.txt");
	}
	
	public String getFacebookLink(){
		return this.getFile("JSON Documents\\Link.txt");
	}
	
	public String getFacebookFriends(){
		return this.getFile("JSON Documents\\Friends.txt");
	}
	
	public String getFacebookGroups(){
		return this.getFile("JSON Documents\\Groups.txt");
	}
	
	public String getFacebookWall(){
		return this.getFile("JSON Documents\\Wall.txt");
	}
	
	public String getFacebookNewsFeeds(){
		return this.getFile("JSON Documents\\NewsFeeds.txt");
	}
	
	public String getFacebookLikes(){
		return this.getFile("JSON Documents\\Likes.txt");
	}
}
