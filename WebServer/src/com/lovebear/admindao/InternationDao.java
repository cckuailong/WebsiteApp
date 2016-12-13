package com.lovebear.admindao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.lovebear.entity.internation;

public class InternationDao extends BaseDao<internation>{

	//find all the news
		public List<internation> internationFindAll(){
			String hql="from internation";
			List<internation> list=internationFind(hql);
			return list;
		}
		
		
		//delete one news by uniquekey
		@SuppressWarnings("unchecked")
		public void internationDelete(final String uniquekey) {
			this.getHibernateTemplate().execute(new HibernateCallback() {  
	            
				@Override  
				public Object doInHibernate(Session session) throws HibernateException,  
				        SQLException {  
				    String hql = "delete from internation data where data.id.uniquekey=?";
				    
				    Query q = session.createQuery(hql);   
				    q.setParameter(0, uniquekey);
				    q.executeUpdate();
				    return null;  
				    }  
				});  
		}

		
		public List<internation> internationFind(final String hql,final Object...params) {
			return getHibernateTemplate().execute(new HibernateCallback<List<internation>>() {

				@Override
				public List<internation> doInHibernate(Session session) throws HibernateException,
						SQLException {
					
					Query q = session.createQuery(hql);
					for(int i=0;i<params.length;i++){
						q.setParameter(i, params[i]);
					}
					List<internation> list = q.list();
					if(list!=null){
						return list;
					}
					return null;
				}
			});
		}
}
