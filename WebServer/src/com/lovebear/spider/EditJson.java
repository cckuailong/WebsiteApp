package com.lovebear.spider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.lovebear.entity.Data;
import com.lovebear.entity.DataId;


public class EditJson extends HibernateDaoSupport {

	private static String str;
	
	public static void readTxtFile(String filePath){
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
	
	public static List<Data> EditJson(){
		
		String filePath="F:\\myjavacode\\app_server\\data.txt";
		String dataPath="F:\\myjavacode\\app_server\\updateData.txt";
		String url="http://v.juhe.cn/toutiao/index?type=top&key=1ce4e176c63e93e0f32ba4b608f6b9b2";
		
		File updateFile=new File(dataPath);
		FileWriter fw = null;
        BufferedWriter writer = null;
        
        try {
			Spider.downloadPage(url);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        readTxtFile(filePath);
		JSONObject  Json=JSON.parseObject(str);
		JSONObject  dataJson=JSONObject.parseObject(Json.get("result").toString());
		JSONArray data=dataJson.getJSONArray("data");
		
        try{
	        fw = new FileWriter(updateFile);
	        writer = new BufferedWriter(fw);
	        List<Data> list = new ArrayList<Data>();
	        Data data2=new Data();
			
			for (int i=0;i<data.size();i++){
				JSONObject o=data.getJSONObject(i);
				if(o.get("title")!=null){
					writer.write(o.get("title")+" ");
				}
				if(o.get("date")!=null){
					writer.write(o.get("date")+" ");
				}
				if(o.get("author_name")!=null){
					writer.write(o.get("author_name")+" ");
				}
				if(o.get("thumbnail_pic_s")!=null){
					writer.write(o.get("thumbnail_pic_s")+" ");
				}
				if(o.get("thumbnail_pic_s02")!=null){
					writer.write(o.get("thumbnail_pic_s02")+" ");
				}
				if(o.get("thumbnail_pic_s03")!=null){
					writer.write(o.get("thumbnail_pic_s03")+" ");
				}
				if(o.get("url")!=null){
					writer.write(o.get("url")+" ");
				}
				if(o.get("uniquekey")!=null){
					writer.write(o.get("uniquekey")+" ");
				}
				if(o.get("type")!=null){
					writer.write(o.get("type")+" ");
				}
				if(o.get("realtype")!=null){
					writer.write(o.get("realtype")+" ");
				}
				writer.write("\n\r");
				data2 = sqlAddData(o.get("title").toString(), o.get("date").toString(), o.get("author_name").toString(), o.get("thumbnail_pic_s").toString(), 
						o.get("thumbnail_pic_s02").toString(), o.get("thumbnail_pic_s03").toString(), 
						o.get("url").toString(), o.get("uniquekey").toString(), o.get("type").toString(), o.get("realtype").toString());
				list.add(data2);
			}
			System.out.println(list);
			return list;
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                writer.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
	}
        public static Data sqlAddData(String title,String date,String author_name,
        		String thumbnail_pic_s,String thumbnail_pic_s02,String thumbnail_pic_s03,
        		String url,String uniquekey,String type,String realtype){
    	    try {
    	    	Data data=new Data(new DataId());
    	    	data.getId().setTitle(title);
    	    	data.getId().setDate(date);
    	    	data.getId().setAuthorName(author_name);
    	    	data.getId().setThumbnailPicS(thumbnail_pic_s);
    	    	data.getId().setThumbnailPicS02(thumbnail_pic_s02);
    	    	data.getId().setThumbnailPicS03(thumbnail_pic_s03);
    	    	data.getId().setUrl(url);
    	    	data.getId().setUniquekey(uniquekey);
    	    	data.getId().setType(type);
    	    	data.getId().setRealtype(realtype);
    	    	System.out.println(data);
    	    	return data;
    	    	
    	    } catch (Exception e) {
    	    	   System.out.println("Failure");
    	    	   e.printStackTrace();
    	    }
			return null;
        }
        
}
