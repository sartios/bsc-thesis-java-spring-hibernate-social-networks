package com.sones.ui;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import com.sones.businessLogic.Facebook.FacebookFriend;
import com.sones.businessLogic.Facebook.FacebookGroup;
import com.sones.businessLogic.Facebook.FacebookGroupList;
import com.sones.businessLogic.Facebook.FacebookRestHandler;
import com.sones.businessLogic.Facebook.FacebookSearchingList;
import com.sones.businessLogic.Facebook.FacebookToken;
import com.sones.dao.TokenDao;

public class FacebookGroupListBox extends JList{

	private DefaultListModel groupNames = null;
	private FacebookGroupList facebookGroups = null;
	
	public FacebookGroupListBox(){
		groupNames = new DefaultListModel();
		TokenDao tokenDao = new TokenDao();
		FacebookToken token = (FacebookToken)tokenDao.findAll().get(0);
		FacebookRestHandler handler=new FacebookRestHandler();
		facebookGroups = handler.getGroups("100000866964787", token.getToken());
		
		List list = facebookGroups.getNamesOfGroups();
		
		for(int i=0;i<list.size();i++){
			groupNames.add(i, list.get(i));
		}
		this.setModel(groupNames);
	}
	
	/**
	 * Add to a FacebookSearchingList the sources from which we will get data
	 * @param sources
	 */
	public void addSelectedSources(FacebookSearchingList sources){
		
		List groups = facebookGroups.getGroups();
		int[] indexes = this.getSelectedIndices();
		/*sources.addID(((FacebookFriend)friends.get(0)).getId());*/
		
		/*String id;*/
		for(int i=0;i<indexes.length;i++){
			String currentID = ((FacebookGroup)groups.get(indexes[i])).getID();
			if(sources.getIDs().contains(currentID)){
				break;
			}
/*			while(null!=(id=sources.getID())){
				if(id==currentID){
					sources.getIDs().
					break;
				}
			}*/
			sources.addID(/*((FacebookFriend)friends.get(i)).getId()*/currentID);	
		}
		//return sources;
	}
	
	/**
	 * Deletes the selected sources from the FacebookSearchingList
	 * @param sources
	 */
	public void deleteSelectedSources(FacebookSearchingList sources){
		List groups = facebookGroups.getGroups();
		int[] indexes = this.getSelectedIndices();
		for(int i=0;i<indexes.length;i++){
			String currentID = ((FacebookGroup)groups.get(indexes[i])).getID();
			sources.deleteID(currentID);
		}
	}

}
