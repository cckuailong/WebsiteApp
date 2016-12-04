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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lovebear.entity.sport;
import com.lovebear.entity.sportId;

public class EditSport {

private String str;
	
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
	
	public List<sport> EditSportJson(String fh,String js,String as){
		
		String filePath="F:\\myjavacode\\app_server\\data\\sport\\data.txt";
		String dataPath="F:\\myjavacode\\app_server\\data\\sport\\updateData.txt";
		String url="http://v.juhe.cn/toutiao/index?type=tiyu&key=1ce4e176c63e93e0f32ba4b608f6b9b2";
		
		return EditAllJson(filePath, dataPath, url,fh,js,as);
	}
	
	
public List<sport> EditAllJson(String filePath,String dataPath,String url,String fh,String js,String as){
				
		EditSport ej = new EditSport(); 
		File updateFile=new File(dataPath);
		FileWriter fw = null;
        BufferedWriter writer = null;
        
        try {
			Spider.downloadPage(url,filePath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        ej.readTxtFile(filePath);
		JSONObject  Json=JSON.parseObject(ej.str);
		JSONObject  dataJson=JSONObject.parseObject(Json.get("result").toString());
		JSONArray data=dataJson.getJSONArray("data");
		
        try{
	        fw = new FileWriter(updateFile);
	        writer = new BufferedWriter(fw);
	        List<sport> list = new ArrayList<sport>();
	        sport data2=new sport();
			
			for (int i=0;i<data.size();i++){
				JSONObject o=data.getJSONObject(i);
				String urltmp=o.get("url").toString();
				if(o.get("title")!=null){
					writer.write(o.get("title")+" ");
				}else{
					continue;
				}
				if(o.get("date")!=null){
					writer.write(o.get("date")+" ");
				}else{
					continue;
				}
				if(o.get("author_name")!=null){
					writer.write(o.get("author_name")+" ");
				}else{
					continue;
				}
				if(o.get("thumbnail_pic_s")!=null){
					writer.write(o.get("thumbnail_pic_s")+" ");
				}else{
					continue;
				}
				if(o.get("url")!=null){
					writer.write(urltmp.substring(0, urltmp.length()-14)+" ");
				}else{
					continue;
				}
				if(true){
					writer.write(i+" ");
				}
				if(o.get("category")!=null){
					writer.write(o.get("category")+" ");
				}else{
					continue;
				}
				writer.write("\n\r");
				
				data2 = ej.sqlAddData("juhe",o.get("title").toString(), o.get("date").toString(), o.get("author_name").toString(), o.get("thumbnail_pic_s").toString(), 
						urltmp.substring(0, urltmp.length()-14), (i+""), o.get("category").toString());
				list.add(data2);
			}
			//凤凰体育
			if(fh!=null){
				isSimilar sim=new isSimilar();
				JSONArray  fhJson=JSON.parseArray(fh);	
				for(int i=0;i<fhJson.size();i++){
					JSONObject fho=fhJson.getJSONObject(i);
					for(int j=0;j<data.size();j++){
						JSONObject o=data.getJSONObject(j);
						if(sim.isSimilar(fho.get("title").toString(), o.get("title").toString()) == true){
							data2=ej.sqlAddData("fenghuang",fho.get("title").toString(), "NAN", "NAN", fho.get("thumbnail").toString(), 
									fho.get("url").toString(), (j+""),o.get("category").toString());
							list.add(data2);
							continue;
						}
					}
				}
			}
			
			//极速体育
			if(js!=null){
				isSimilar sim=new isSimilar();
				JSONArray  jsJson=JSON.parseArray(js);	
				for(int i=0;i<jsJson.size();i++){
					JSONObject jso=jsJson.getJSONObject(i);
					for(int j=0;j<data.size();j++){
						JSONObject o=data.getJSONObject(j);
						if(sim.isSimilar(jso.get("title").toString(), o.get("title").toString()) == true){
							data2=ej.sqlAddData("jisu",jso.get("title").toString(), jso.get("time").toString(), jso.get("src").toString(),
									jso.get("pic").toString(), jso.get("url").toString(), (j+""),o.get("category").toString());
							list.add(data2);
							continue;
						}
					}
				}
			}
			
			//avadar体育
			if(as!=null){
				isSimilar sim=new isSimilar();
				JSONArray  asJson=JSON.parseArray(as);	
				for(int i=0;i<asJson.size();i++){
					JSONObject aso=asJson.getJSONObject(i);
					for(int j=0;j<data.size();j++){
						JSONObject o=data.getJSONObject(j);
						if(sim.isSimilar(aso.get("title").toString(), o.get("title").toString()) == true){
							data2=ej.sqlAddData("avadar",aso.get("title").toString(), aso.get("ctime").toString(), aso.get("description").toString(),
									aso.get("picUrl").toString(), aso.get("url").toString(), (j+""),o.get("category").toString());
							list.add(data2);
							continue;
						}
					}
				}
			}
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
	
        public sport sqlAddData(String source,String title,String date,String author_name,
        		String thumbnail_pic_s, String url,String uniquekey,String type){
    	    try {
    	    	sport data=new sport(new sportId());
    	    	data.getId().setSource(source);;
    	    	data.getId().setTitle(title);
    	    	data.getId().setDate(date);
    	    	data.getId().setAuthorName(author_name);
    	    	data.getId().setThumbnailPicS(thumbnail_pic_s);
    	    	data.getId().setUrl(url);
    	    	data.getId().setUniquekey(uniquekey);
    	    	data.getId().setType(type);
    	    	return data;
    	    	
    	    } catch (Exception e) {
    	    	   System.out.println("Failure");
    	    	   e.printStackTrace();
    	    }
			return null;
        }
}
