package com.lovebear.dao;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.net.jsse.JSSEImplementation;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import sinaSpider.sinaInternation;
import sinaSpider.sinaSociety;
import tencentSpider.tencentInternation;
import tencentSpider.tencentMilitary;
import tencentSpider.tencentSociety;

import com.alibaba.fastjson.JSON;
import com.lovebear.entity.Data;
import com.lovebear.entity.entertain;
import com.lovebear.entity.finance;
import com.lovebear.entity.internation;
import com.lovebear.entity.military;
import com.lovebear.entity.society;
import com.lovebear.entity.sport;
import com.lovebear.entity.tech;
import com.lovebear.spider.AvatarSpider;
import com.lovebear.spider.EditEntertain;
import com.lovebear.spider.EditFinance;
import com.lovebear.spider.EditInternation;
import com.lovebear.spider.EditJson;
import com.lovebear.spider.EditMilitary;
import com.lovebear.spider.EditSociety;
import com.lovebear.spider.EditSport;
import com.lovebear.spider.EditTech;
import com.lovebear.spider.FengHSpider;
import com.lovebear.spider.jisuSpider;

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
					System.out.print("the getData List is null");
				}
			}
			data=data.substring(0, data.length()-1);
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
	
	public void queryToRefreshData(String hint) {
		if(hint.equals("1")){	
			String[] tables=new String[]{"Data","society","internation",
					"entertain","sport","military","tech","finance"};
			//clear the database
			for(String table: tables){
				clearData(table);
			}
			//update the database
			queryToUpdate();
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
	public void clearData(final String table) {
		this.getHibernateTemplate().execute(new HibernateCallback() {  
            
			@Override  
			public Object doInHibernate(Session session) throws HibernateException,  
			        SQLException {  
			    String hql = "delete from "+table;
			    
			    Query q = session.createQuery(hql);    
			    q.executeUpdate();
			    return null;  
			    }  
			});  
	}
	
	
	public void queryToUpdate(){
		List<Data> list1 = new ArrayList<Data>();
		List<society> list2 = new ArrayList<society>();
		List<internation> list3 = new ArrayList<internation>();
		List<entertain> list4 = new ArrayList<entertain>();
		List<sport> list5 = new ArrayList<sport>();
		List<military> list6 = new ArrayList<military>();
		List<tech> list7 = new ArrayList<tech>();
		List<finance> list8 = new ArrayList<finance>();
		//凤凰整合新闻
		FengHSpider fhSpider=new FengHSpider();
		fhSpider.getAllJason();
		//极速整合新闻
		jisuSpider jsSpider=new jisuSpider();
		jsSpider.getAllJson();
		//avatar整合新闻
		AvatarSpider avSpider=new AvatarSpider();
		avSpider.getAllJson();
		//sina Society
		sinaSociety sSociety=new sinaSociety();
		sSociety.extractDiv("http://news.sina.com.cn/society/");
		//sina Internation
		sinaInternation sInternation=new sinaInternation();
		sInternation.extractDiv("http://news.sina.com.cn/world/");
		//tencent Society
		tencentSociety tSociety=new tencentSociety();
		tSociety.extractDiv("http://news.qq.com/society_index.shtml");
		//tencent Internation
		tencentInternation tInternation=new tencentInternation();
		tInternation.extractDiv("http://news.qq.com/world_index.shtml");
		//tencent Military
		tencentMilitary tMilitary=new tencentMilitary();
		tMilitary.extractDiv("http://mil.qq.com/mil_index.htm");
		
		EditJson ej=new EditJson();
		EditSociety es=new EditSociety();
		EditInternation ei=new EditInternation();
		EditEntertain ee=new EditEntertain();
		EditSport esp=new EditSport();
		EditMilitary em=new EditMilitary();
		EditTech et=new EditTech();
		EditFinance ef=new EditFinance();
		//前3个参数为凤凰，极速，avatar
		list1=ej.EditTopJson(fhSpider.getFhList().get(0),
								jsSpider.getJsList().get(0),
								null);
		list2=es.EditSocietyJson(null,
								null,
								null,
								sSociety.getSdList(),    //新浪社会
								tSociety.getTsList());   //腾讯社会
		list3=ei.EditInternationJson(null,
									null,
									avSpider.getAvList().get(0),
									sInternation.getSdList(),   //新浪国际
									tInternation.getTiList());   //腾讯国际
		list4=ee.EditEntertainJson(fhSpider.getFhList().get(7),
									jsSpider.getJsList().get(3),
									null);
		list5=esp.EditSportJson(fhSpider.getFhList().get(2),
									jsSpider.getJsList().get(2),
									avSpider.getAvList().get(1));
		list6=em.EditMilitaryJson(fhSpider.getFhList().get(3),
									jsSpider.getJsList().get(4),
									null,
									tMilitary.getTmList());   //腾讯军事
		list7=et.EditTechJson(fhSpider.getFhList().get(4),
								jsSpider.getJsList().get(5),
								avSpider.getAvList().get(2));
		list8=ef.EditFinanceJson(fhSpider.getFhList().get(1),
									jsSpider.getJsList().get(1),
									null);
		
		
		
		for(Data entity:list1){
			getHibernateTemplate().merge(entity);
		}
		for(society entity:list2){
			getHibernateTemplate().merge(entity);
		}
		for(internation entity:list3){
			getHibernateTemplate().merge(entity);
		}
		
		for(entertain entity:list4){
			getHibernateTemplate().merge(entity);
			
		}
		
		for(sport entity:list5){
			getHibernateTemplate().merge(entity);
		}
		
		for(military entity:list6){
			getHibernateTemplate().merge(entity);
		}
		for(tech entity:list7){
			getHibernateTemplate().merge(entity);
		}
		
		for(finance entity:list8){
			getHibernateTemplate().merge(entity);
		}
		
	}
	
}
