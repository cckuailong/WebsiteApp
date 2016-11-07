package com.lovebear.actions;

import com.lovebear.entity.Result;
import com.lovebear.service.DataService;

public class DataAction extends BaseAction{

	private String str;
	private DataService dataService;
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public DataService getDataService() {
		return dataService;
	}

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

	public void getData(){
		Result<String> result = dataService.queryToResult(str);
		print(result);
	}
	
}
