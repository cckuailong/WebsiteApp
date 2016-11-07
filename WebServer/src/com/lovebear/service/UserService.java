package com.lovebear.service;

import org.apache.catalina.util.Base64;

import com.lovebear.dao.UserDao;
import com.lovebear.entity.Result;
import com.lovebear.entity.User;

public class UserService {

	private static int uid;
	private UserDao userDao;
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public UserService(){
		UserService.uid=0;
	}

	public Result<User> queryToResult(User u){
		Result<User> result = new Result<User>();
		String account = u.getId().getPhone()==null?u.getId().getEmail():u.getId().getPhone();
		if(account == null){
			result.state = Result.STATE_FAIL;
			result.descript = "The Account Can't Be Empty";
			return result;
		}
		User qr = userDao.queryToLogin(account, u.getId().getPwd());
		if(qr != null){
			result.state=Result.STATE_SUC;
			result.descript="Login Successfully";
			result.data = qr;
		}else{
			result.state = Result.STATE_FAIL;
			result.descript = "UserName doesn't exit";
		}
		return result;
	}
	
	public Result<Boolean> register(User u) {
		Result<Boolean> result = new Result<Boolean>();
		try {
			if(u.getId().getUid()==null){
				uid++;
				u.getId().setUid(Integer.toString(uid));
			}
			if(u.getId().getToken() == null){
				String token = u.getId().getPhone()+u.getId().getEmail()
						+System.currentTimeMillis();
				token = Base64.encode(token.getBytes());
				u.getId().setToken(token);
			}
			userDao.save(u);
			result.state = Result.STATE_SUC;
			result.descript = "Register Successfully";
			result.data = true;
		} catch (Exception e) {
			result.state = Result.STATE_FAIL;
			result.descript = "Register Failure";
			result.data=false;
			e.printStackTrace();
		}
		return result;
	}
}
