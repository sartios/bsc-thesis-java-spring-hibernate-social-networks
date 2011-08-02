package com.sones.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.Facebook.Source.FacebookGroup;
import com.sones.businessLogic.Facebook.Source.FacebookGroupList;

public class FacebookGroupDaoTest {

	private FacebookGroupDao dao_;
	private FacebookGroup group_;
	private String name_;
	private String id_;
	
	@Before
	public void setUp(){
		dao_ = new FacebookGroupDao();
		name_="Friend Name";
		id_="Friend ID";
		group_=new FacebookGroup();
		group_.setName(name_);
		group_.setId(id_);
	}
	
	@After
	public void tearDown(){
		dao_=null;
	}
	
	@Test
	public void saveOrUpdate_ConcreteFacebookGroup_Test(){
		dao_.saveOrUpdate(group_);
		assertNotNull(dao_.find(id_));
	}
	
	@Test
	public void saveOrUpdate_FacebookGroupExists_Test(){
		dao_.saveOrUpdate(group_);
		dao_.saveOrUpdate(group_);
		assertNotNull(dao_.find(id_));
	}
	
	@Test
	public void saveOrUpdate_FacebookGroupIsNull_Test(){
		boolean notThrowing=false;
		try{
			dao_.saveOrUpdate(null);
			notThrowing=true;
		}
		catch (Exception e) {
			notThrowing=false;
		}
		assertTrue(notThrowing);
	}
	
	@Test
	public void saveOrUpdate_GroupHasNotName_Test(){
		String groupID="Has not name";
		FacebookGroup group=new FacebookGroup();
		group.setName("");
		group.setId(groupID);
		assertNull(dao_.find(groupID));		
	}
	
	@Test
	public void saveOrUpdate_GroupHasNotID_Test(){
		String groupName="Has not id";
		FacebookGroup group=new FacebookGroup();
		group.setName(groupName);
		group.setId("");
		dao_.saveOrUpdate(group);
		assertNull(dao_.find(groupName));		
	}
	
	@Test
	public void saveOrUpdate_GroupHasNotIDAndHasNotName_Test(){
		String groupName="";
		String groupID="";
		FacebookGroup group=new FacebookGroup();
		group.setName(groupName);
		group.setId(groupID);
		dao_.saveOrUpdate(group);
		assertNull(dao_.find(groupID));		
	}
	
	@Test
	public void delete_GroupExists_Test(){
		FacebookGroup group=new FacebookGroup();
		group.setName(name_);
		group.setId(id_);
		dao_.saveOrUpdate(group_);
		assertTrue(dao_.delete(group));
	}
	
	@Test
	public void delete_GroupDoesNotExist_Test(){
		String name="Doesn't exist";
		String id="Doesn't exist";
		FacebookGroup group=new FacebookGroup();
		group.setName(name);
		group.setId(id);
		dao_.delete(group);
		assertTrue(dao_.delete(group));
	}
	
	@Test
	public void delete_GroupWithNoName_Test(){
		String name="";
		String id="Friend with no name";
		FacebookGroup group=new FacebookGroup();
		group.setName(name);
		group.setId(id);
		assertFalse(dao_.delete(group));
	}
	
	@Test
	public void delete_GroupWithNoID_Test(){
		String name="Friend with no id";
		String id="";
		FacebookGroup group=new FacebookGroup();
		group.setName(name);
		group.setId(id);
		assertFalse(dao_.delete(group));
	}
	
	@Test
	public void delete_GroupIsNull_Test(){
		assertFalse(dao_.delete(null));
	}
	
	@Test
	public void find_GroupExists_Test(){
		dao_.saveOrUpdate(group_);
		assertNotNull(dao_.find(id_));
	}
	
	@Test
	public void find_GroupDoesNotExist_Test(){
		dao_.delete(group_);
		assertNull(dao_.find(id_));
	}
	
	@Test
	public void find_IdIsNull_Test(){
		assertNull(dao_.find(null));
	}
	
	@Test
	public void findGroupByName_NameExists_Test(){
		dao_.saveOrUpdate(group_);
		assertNotNull(dao_.findByName(name_));
	}
	
	@Test
	public void findGroupByName_GroupDoesNotExist_Test(){
		dao_.delete(group_);
		assertNull(dao_.findByName(name_));
	}
	
	@Test
	public void findGroupByName_IdIsNull_Test(){
		assertNull(dao_.findByName(null));
	}
	
	@Test
	public void findAll_ThereAreGroups_Test(){
		FacebookGroupList groups = dao_.findAll();
		assertFalse(groups.getGroups().isEmpty());
	}
	
	@Test
	public void findAll_ThereAreNotGroups_Test(){
		FacebookGroupList groups = dao_.findAll();
		deleteAllGroups(groups);
		FacebookGroupList groupsAfterDeleteThem=dao_.findAll();
		saveAllGroups(groups);
		assertTrue(groupsAfterDeleteThem.getGroups().isEmpty());
		
		
	}
	
	private void deleteAllGroups(final FacebookGroupList groups){
		List<FacebookGroup> listOfGroups = groups.getGroups();
		int groupSize = listOfGroups.size();
		for(int i=0;i<groupSize;i++){
			dao_.delete(listOfGroups.get(i));
		}
	}
	
	private void saveAllGroups(final FacebookGroupList groups){
		List<FacebookGroup> listOfGroups = groups.getGroups();
		int groupSize = listOfGroups.size();
		for(int i=0;i<groupSize;i++){
			dao_.saveOrUpdate(listOfGroups.get(i));
		}
	}
}
