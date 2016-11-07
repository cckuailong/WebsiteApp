package com.lovebear.dao;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.alibaba.fastjson.JSON;
import com.lovebear.entity.Data;
import com.lovebear.spider.EditJson;

public class OperateDao extends BaseDao<List<Data>>{
	public String queryToGetData(String hint) {
		String data="";
		String finaldata="";
		if(hint.equals("1")){
			clearData();
			queryToUpdate();
			String hql = "select id from Data";
			List<Data> list = queryData(hql);
			if(list!=null){
				for (int i=0;i<list.size();i++){
					data+=JSON.toJSON(list.get(i)).toString();
				}
				
				//trim the data
				finaldata=data.replace("\\\"", "\"");
				
				//base64 encode to transport
				try {
					finaldata=new String(Base64.encodeBase64(finaldata.getBytes("utf-8")), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
			}else{
				System.out.print("hhhh");
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
	
	@SuppressWarnings("unchecked")
	public void clearData() {
		this.getHibernateTemplate().execute(new HibernateCallback() {  
            
			@Override  
			public Object doInHibernate(Session session) throws HibernateException,  
			        SQLException {  
			    String hql = "delete from Data";
			    
			    Query q = session.createQuery(hql);    
			    q.executeUpdate();
			    return null;  
			    }  
			});  
	}
	
	
	public void queryToUpdate(){
		List<Data> list = new ArrayList<Data>();
		list=EditJson.EditJson();
		for(Data entity:list){
			getHibernateTemplate().save(entity);
		}
	}
}
