package com.lovebear.admindao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.lovebear.entity.Data;

public class TopDao extends BaseDao<List<Data>>{

	//find all the news
	public List<Data> topFindAll(){
		String hql="from Data";
		List<Data> list=topFind(hql);
		return list;
	}
	
	
	//delete one news by uniquekey
	@SuppressWarnings("unchecked")
	public void topDelete(final String uniquekey) {
		this.getHibernateTemplate().execute(new HibernateCallback() {  
            
			@Override  
			public Object doInHibernate(Session session) throws HibernateException,  
			        SQLException {  
			    String hql = "delete from Data data where data.id.uniquekey=?";
			    
			    Query q = session.createQuery(hql);   
			    q.setParameter(0, uniquekey);
			    q.executeUpdate();
			    return null;  
			    }  
			});  
	}

	
	public List<Data> topFind(final String hql,final Object...params) {
		return getHibernateTemplate().execute(new HibernateCallback<List<Data>>() {

			@Override
			public List<Data> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query q = session.createQuery(hql);
				for(int i=0;i<params.length;i++){
					q.setParameter(i, params[i]);
				}
				List<Data> list = q.list();
				if(list!=null){
					return list;
				}
				return null;
			}
		});
	}

}
