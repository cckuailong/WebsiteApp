package com.lovebear.dao;

import com.lovebear.entity.User;

public class UserDao extends BaseDao<User>{
	public User queryToLogin(String account,String pwd) {
		String hql = "from userinfo where (email=? and pwd=?) or (phone=? and pwd=?)";
		User u = queryEntity(hql, account, pwd, account, pwd);
		return u;
	}
	
	public void save(User u){
		super.save(u);
	}
}
