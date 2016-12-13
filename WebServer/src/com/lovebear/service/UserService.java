package com.lovebear.service;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

import com.lovebear.dao.UserDao;
import com.lovebear.entity.Result;
import com.lovebear.entity.User;
import com.lovebear.entity.UserId;

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

	public Result<UserId> queryToResult(User u){
		Result<UserId> result = new Result<UserId>();
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
			result.data = qr.getId();
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
				token = new String(Base64.encodeBase64(token.getBytes()));
				u.getId().setToken(token);
			}
			if(u.getId().getGender()==null){
				u.getId().setGender("NAN");
			}
			if(u.getId().getNickname()==null){
				u.getId().setNickname("NAN");
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
	
	public void updateUserinfo(User u){
		String pwd,gender,nickname,phone;
		pwd=u.getId().getPwd();
		gender=u.getId().getGender();
		nickname=u.getId().getNickname();
		phone=u.getId().getPhone();
		if(pwd==null){
			pwd="NAN";
		}
		if(gender==null){
			gender="NAN";
		}else{
			try {
				gender=new String(Base64.decodeBase64(gender.getBytes()),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(nickname==null){
			nickname="NAN";
		}else{
			try {
				nickname=new String(Base64.decodeBase64(nickname.getBytes()),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		userDao.queryToupdate(pwd, gender, nickname, phone);
	}
}
