package com.sones.businessLogic.Facebook.Source;

import net.sf.ezmorph.MorphException;

import org.apache.commons.beanutils.DynaBean;

/**
 * This class is responsible for creating acceptance groups.
 * It implements the singleton pattern
 * @author Sartios
 */
public class FacebookGroupFactory {
	
	/**
	 * This instance 
	 */
	private static FacebookGroupFactory instance_=null;
	
	private FacebookGroupFactory(){
		
	}
	
	public static FacebookGroupFactory getInstance(){
		if(instance_==null){
			instance_ = new FacebookGroupFactory();
		}
		return instance_;
	}
	
	public FacebookGroup getGroup(DynaBean bean){
		String id = null;
		String name = null;
		String description = null;
		try{
			id = bean.get("id").toString();
		}
		catch(MorphException e){}
		try{
			name = bean.get("name").toString();
		}
		catch(MorphException e){}
		try{
			description = bean.get("description").toString();
		}
		catch(MorphException e){}
		return new FacebookGroup(id, name, description);
	}
}
