package com.lovebear.admindao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.lovebear.entity.military;

public class MilitaryDao extends BaseDao<military>{

	//find all the news
		public List<military> militaryFindAll(){
			String hql="from military";
			List<military> list=militaryFind(hql);
			return list;
		}
		
		
		//delete one news by uniquekey
		@SuppressWarnings("unchecked")
		public void militaryDelete(final String uniquekey) {
			this.getHibernateTemplate().execute(new HibernateCallback() {  
	            
				@Override  
				public Object doInHibernate(Session session) throws HibernateException,  
				        SQLException {  
				    String hql = "delete from military data where data.id.uniquekey=?";
				    
				    Query q = session.createQuery(hql);   
				    q.setParameter(0, uniquekey);
				    q.executeUpdate();
				    return null;  
				    }  
				});  
		}

		
		public List<military> militaryFind(final String hql,final Object...params) {
			return getHibernateTemplate().execute(new HibernateCallback<List<military>>() {

				@Override
				public List<military> doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					Query q = session.createQuery(hql);
					for(int i=0;i<params.length;i++){
						q.setParameter(i, params[i]);
					}
					List<military> list = q.list();
					if(list!=null){
						return list;
					}
					return null;
				}
			});
		}
}
