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
	
	public String getFacebookNote(){
		return this.getFile("JSON Documents\\Note.txt");
	}
	
	public String getFacebookVideo(){
		return this.getFile("JSON Documents\\Video.txt");
	}
	
	public String getFacebookCheckin(){
		return this.getFile("JSON Documents\\Checkin.txt");
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
	
	public String getFacebookUser(){
		return this.getFile("JSON Documents\\FacebookUser.txt");

	}
	
	public String getWallWithMoreComments(){
		return this.getFile("JSON Documents\\WallWithMoreComments.txt");
	}
	
	public String getWallWithSameUserPosts()
	{
		return this.getFile("JSON Documents\\WallSameUser.txt");
	}
	
	public	String	GetGreekStatusMessage()
	{
		return this.getFile("JSON Documents\\GreekStatusMessage.txt");
	}
	
	public	String	GetPublicPlaces()
	{
		return this.getFile("JSON Documents\\PublicPlaces.txt");
	}
}
