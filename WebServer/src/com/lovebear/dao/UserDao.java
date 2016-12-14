package com.lovebear.dao;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.lovebear.entity.User;
import com.opensymphony.xwork2.inject.util.FinalizablePhantomReference;

public class UserDao extends BaseDao<User>{
	public User queryToLogin(String account,String pwd) {
		String hql = "from User where (email=? and pwd=?) or (phone=? and pwd=?)";
		User u = queryEntity(hql, account, pwd, account, pwd);
		return u;
	}
	
	public void save(User u){
		super.save(u);
	}
	
	@SuppressWarnings("unchecked")
	public void queryToupdate(final String pwd,final String gender,final String nickname,final String phone) {
		this.getHibernateTemplate().execute(new HibernateCallback() {  
            
			@Override  
			public Object doInHibernate(Session session) throws HibernateException,  
			        SQLException {  
			    String hql = "update User user set user.id.pwd=?,user.id.gender=?,user.id.nickname=? where user.id.phone=?";
			    
			    Query q = session.createQuery(hql);    
				q.setParameter(0, pwd);
				q.setParameter(1, gender);
				q.setParameter(2, nickname);
				q.setParameter(3, phone);
			    q.executeUpdate();
			    return null;  
			    }  
			});  
	}
}
