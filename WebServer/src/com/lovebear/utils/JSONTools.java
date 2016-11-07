package com.lovebear.utils;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lovebear.entity.Result;

public class JSONTools {

	public static String toJsonString(Object o,String...params) {
		List<String> list = toList(params);
		Object o2 = JSON.parse(JSON.toJSONString(o));
		System.out.println(o2);
		filterParam(o2,list);
		return o2.toString();
	}

	private static void filterParam(Object o, List<String> list) {
		if(o instanceof JSONArray){
			JSONArray ja=(JSONArray) o;
			for(int i=0;i<ja.size();i++){
				Object o2=ja.get(i);
				filterParam(o2,list);
			}
		}else if(o instanceof JSONObject){
			JSONObject jo = (JSONObject) o;
			Set<Entry<String, Object>> set = jo.entrySet();
			Iterator<Entry<String, Object>> it = set.iterator();
			while(it.hasNext()){
				Entry<String, Object> entry=it.next();
				if(list.contains(entry.getKey())){
					it.remove();
				}else{
					Object o3=entry.getValue();
					filterParam(o3, list);
				}
			}
		}else{
			return;
		}
	}
	
	private static List<String> toList(String...params){
		List<String> list = new LinkedList<String>();
		for(String str: params){
			list.add(str);
		}
		return list;
	}
	
}
