package com.lovebear.spider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lovebear.entity.internation;

public class jisuSpider {
	private String str;
	private ArrayList<String> jsList=new ArrayList<String>();

	public ArrayList<String> getJsList() {
		return jsList;
	}

	public void setJsList(ArrayList<String> jsList) {
		this.jsList = jsList;
	}

	public void jisuDowload(){
		
		String[] pathList={"%E5%A4%B4%E6%9D%A1","%E8%B4%A2%E7%BB%8F","%E4%BD%93%E8%82%B2","%E5%A8%B1%E4%B9%90",
                "%E5%86%9B%E4%BA%8B","%E7%A7%91%E6%8A%80"};
		String[] filenameLiString={"top","finance","sport","entertain",
                        "military","tech"};
		for(int i=0;i<pathList.length;i++){
			String path="http://api.jisuapi.com/news/get?channel="+pathList[i]+"&start=0&num=20&appkey=beb00ed22ce29be7";
			String filename="/data/jisu/"+filenameLiString[i]+".txt";
			try {
				Spider.downloadPage(path, filename);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void readTxtFile(String filePath){
	    try {
	            String encoding="UTF-8";
	            str="";
	            File file=new File(filePath);
	            if(file.isFile() && file.exists()){
	                InputStreamReader read = new InputStreamReader(
	                new FileInputStream(file),encoding);
	                BufferedReader bufferedReader = new BufferedReader(read);
	                String lineTxt = null;
	                while((lineTxt = bufferedReader.readLine()) != null){
	                    str+=lineTxt;
	                }
	                read.close();
		        }else{
		            System.out.println("file read error");
		        }
	    } catch (Exception e) {
	        System.out.println("content error");
	        e.printStackTrace();
	    }
	 
	}
	
	public void getAllJson(){
		String[] filenameLiString={"top","finance","sport","entertain",
                "military","tech"};
		String filename;
		
		jisuDowload();
		
		for(int i=0;i<filenameLiString.length;i++){
			filename="/data/jisu/"+filenameLiString[i]+".txt";
			readTxtFile(filename);
			JSONObject  Json=JSON.parseObject(str);
			JSONObject  predataJson=JSONObject.parseObject(Json.get("result").toString());
			JSONArray  dataJson=JSONObject.parseArray(predataJson.get("list").toString());
			jsList.add(dataJson.toString());
		}
	}
	
}
