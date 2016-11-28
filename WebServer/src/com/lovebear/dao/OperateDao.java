package com.lovebear.dao;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.alibaba.fastjson.JSON;
import com.lovebear.entity.Data;

public class OperateDao extends BaseDao<List<Data>>{
	public String queryToGetData(String hint) {
		String data="";
		String[] tables=new String[]{"Data","society","internation",
				"entertain","sport","military","tech","finance"};
		String finaldata="";
		if(hint.equals("1")){
			
			//get data
			data+='[';
			for(String table: tables){
				String hql = "select id from "+table;
				List<Data> list = queryData(hql);
				if(list!=null){				
					for (int i=0;i<list.size();i++){
						data+=JSON.toJSON(list.get(i)).toString();
						data+=',';
					}
					
				}else{
					System.out.print("hhhh");
				}
			}
			//data.substring(0, data.length()-2);
			data+=']';
			//trim the data
			finaldata=data.replace("\\\"", "\"");	
			System.out.println(finaldata);
			//base64 encode to transport
			try {
				finaldata=new String(Base64.encodeBase64(finaldata.getBytes("utf-8")), "UTF-8");
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return finaldata; 
		}else{
			return null;
		}
		
	}
	
	public List<Data> queryData(final String hql) {
		return getHibernateTemplate().execute(new HibernateCallback<List<Data>>() {

			@Override
			public List<Data> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query q = session.createQuery(hql);
				
				List<Data> list = q.list();
				if(list!=null){
					return list;
				}
				return null;
			}
		});
	}
	
	
}
