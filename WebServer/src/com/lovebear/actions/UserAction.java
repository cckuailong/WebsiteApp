package com.lovebear.actions;

import com.lovebear.entity.Result;
import com.lovebear.entity.User;
import com.lovebear.service.UserService;

public class UserAction extends BaseAction{
	private User u;
	private UserService userService;
	
	
	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void login(){
		Result<User> result = userService.queryToResult(u);
		print(result, "pwd");
	}
	
	public void register() {
		Result<Boolean> result = userService.register(u);
		print(result);
	}
}
