package com.lovebear.admindao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.lovebear.entity.sport;

public class SportDao extends BaseDao<sport>{

	//find all the news
		public List<sport> sportFindAll(){
			String hql="from sport";
			List<sport> list=sportFind(hql);
			return list;
		}
		
		
		//delete one news by uniquekey
		@SuppressWarnings("unchecked")
		public void sportDelete(final String uniquekey) {
			this.getHibernateTemplate().execute(new HibernateCallback() {  
	            
				@Override  
				public Object doInHibernate(Session session) throws HibernateException,  
				        SQLException {  
				    String hql = "delete from sport data where data.id.uniquekey=?";
				    
				    Query q = session.createQuery(hql);   
				    q.setParameter(0, uniquekey);
				    q.executeUpdate();
				    return null;  
				    }  
				});  
		}

		
		public List<sport> sportFind(final String hql,final Object...params) {
			return getHibernateTemplate().execute(new HibernateCallback<List<sport>>() {

				@Override
				public List<sport> doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					Query q = session.createQuery(hql);
					for(int i=0;i<params.length;i++){
						q.setParameter(i, params[i]);
					}
					List<sport> list = q.list();
					if(list!=null){
						return list;
					}
					return null;
				}
			});
		}
}
