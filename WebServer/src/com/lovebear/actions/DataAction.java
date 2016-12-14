package com.lovebear.actions;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import com.alibaba.fastjson.JSON;
import com.lovebear.entity.Result;
import com.lovebear.service.DataService;
import com.lovebear.utils.JSONTools;

public class DataAction extends BaseAction{

	private String str;
	private DataService dataService;
	private Result<String> result;
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public DataService getDataService() {
		return dataService;
	}
	

	public Result<String> getResult() {
		return result;
	}

	public void setResult(Result<String> result) {
		this.result = result;
	}

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

	public void getData(){
		this.result=dataService.queryToResult(str);
		print(this.result);
		//return SUCCESS;
	}

	public void refreshData(){
		this.result=dataService.quertyToRefreshResult(str);
		print(this.result);
		//return SUCCESS;
	}
	
}
