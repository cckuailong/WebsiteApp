package com.lovebear.actions;

import com.lovebear.entity.Result;
import com.lovebear.service.DataService;

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

	public String getData(){
		this.result=dataService.queryToResult(str);
		return SUCCESS;
	}

	public String refreshData(){
		dataService.quertyToRefreshResult(str);
		return SUCCESS;
	}
	
}
