package com.lovebear.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.support.DaoSupport;

import com.lovebear.dao.AdminDao;
import com.lovebear.entity.Data;
import com.opensymphony.xwork2.ActionSupport;

public class adminLogin extends ActionSupport{

	private String user;
	private String pwd;
	private AdminDao adminDao;
	private Data da;
	private String uniquekey;
	private List<Data> data = new ArrayList<Data>();
	
	
	public String getUniquekey() {
		return uniquekey;
	}

	public void setUniquekey(String uniquekey) {
		this.uniquekey = uniquekey;
	}

	public Data getDa() {
		return da;
	}

	public void setDa(Data da) {
		this.da = da;
	}

	
	public AdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	
	public String adminLogin(){
		if(user.equals("lovebear") && pwd.equals("admin")){
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	public String viewData() {
		
		data = adminDao.adminFindAll();

		return SUCCESS;
	}
	
	public String deleteByUniquekey() {
		adminDao.adminDelete(uniquekey);
		return SUCCESS;
	}
	
}
