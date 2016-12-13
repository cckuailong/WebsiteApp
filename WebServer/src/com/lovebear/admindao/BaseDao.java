package com.lovebear.admindao;

import java.util.List;
import java.sql.SQLException;

import org.hibernate.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.alibaba.fastjson.JSON;

public class BaseDao<T> extends HibernateDaoSupport{
	
	protected T queryEntity(final String hql,final Object...params) {
		return getHibernateTemplate().execute(new HibernateCallback<T>() {

			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query q = session.createQuery(hql);
				for(int i=0;i<params.length;i++){
					q.setParameter(i, params[i]);
				}
				List<T> list = q.list();
				if(list!=null&&list.size()>0){
					return list.get(0);
				}
				return null;
			}
		});
	}
	
	public void save(Object o) {
		getHibernateTemplate().save(o);
	}
	

}
