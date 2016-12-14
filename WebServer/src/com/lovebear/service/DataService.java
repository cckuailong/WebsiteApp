package com.lovebear.service;

import com.lovebear.dao.OperateDao;
import com.lovebear.entity.Result;

public class DataService {

	private OperateDao operateDao;
	public OperateDao getOperateDao() {
		return operateDao;
	}
	public void setOperateDao(OperateDao operateDao) {
		this.operateDao = operateDao;
	}
	public Result<String> queryToResult(String hint){
		Result<String> result = new Result<String>();
		String str=operateDao.queryToGetData(hint);
		if(str != null){
			result.state=Result.STATE_SUC;
			result.descript="GetData Successfully";
			result.data = str;
		}else{
			result.state = Result.STATE_FAIL;
			result.descript = "GetData Failed";
		}
		return result;
	}
	
	public Result<String> quertyToRefreshResult(String hint) {
		Result<String> result = new Result<String>();
		try{
			operateDao.queryToRefreshData(hint);
			result.state=Result.STATE_SUC;
			result.descript="RefreshData Successfully";
		}catch (Exception e) {
			result.state = Result.STATE_FAIL;
			result.descript = "Refresh Failure";
			e.printStackTrace();
		}
		return result;
	}
	
}
