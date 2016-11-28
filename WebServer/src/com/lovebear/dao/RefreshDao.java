package com.lovebear.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class RefreshDao extends HibernateDaoSupport{

	
	public void refreshDao(){
		/*
		String[] tables=new String[]{"Data","society","internation",
				"entertain","sport","military","tech","finance"};
		//clear the database
		for(String table: tables){
			clearData(table);
		}
		//update the database
		queryToUpdate();
		*/
		System.out.println("refresh");
	}
	/*
	@SuppressWarnings("unchecked")
	public void clearData(final String table) {
		this.getHibernateTemplate().execute(new HibernateCallback() {  
            
			@Override  
			public Object doInHibernate(Session session) throws HibernateException,  
			        SQLException {  
			    String hql = "delete from "+table;
			    
			    Query q = session.createQuery(hql);    
			    q.executeUpdate();
			    return null;  
			    }  
			});  
	}
	
	
	public void queryToUpdate(){
		List<Data> list1 = new ArrayList<Data>();
		List<society> list2 = new ArrayList<society>();
		List<internation> list3 = new ArrayList<internation>();
		List<entertain> list4 = new ArrayList<entertain>();
		List<sport> list5 = new ArrayList<sport>();
		List<military> list6 = new ArrayList<military>();
		List<tech> list7 = new ArrayList<tech>();
		List<finance> list8 = new ArrayList<finance>();
		
		EditJson ej=new EditJson();
		EditSociety es=new EditSociety();
		EditInternation ei=new EditInternation();
		EditEntertain ee=new EditEntertain();
		EditSport esp=new EditSport();
		EditMilitary em=new EditMilitary();
		EditTech et=new EditTech();
		EditFinance ef=new EditFinance();
		
		
		list1=ej.EditTopJson();
		list2=es.EditSocietyJson();
		list3=ei.EditInternationJson();
		list4=ee.EditEntertainJson();
		list5=esp.EditSportJson();
		list6=em.EditMilitaryJson();
		list7=et.EditTechJson();
		list8=ef.EditFinanceJson();
		
		for(Data entity:list1){
			getHibernateTemplate().save(entity);
		}
		for(society entity:list2){
			getHibernateTemplate().save(entity);
		}
		for(internation entity:list3){
			getHibernateTemplate().save(entity);
		}
		for(entertain entity:list4){
			getHibernateTemplate().save(entity);
		}
		for(sport entity:list5){
			getHibernateTemplate().save(entity);
		}
		for(military entity:list6){
			getHibernateTemplate().save(entity);
		}
		for(tech entity:list7){
			getHibernateTemplate().save(entity);
		}
		for(finance entity:list8){
			getHibernateTemplate().save(entity);
		}
	}
	*/
}
