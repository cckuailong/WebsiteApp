package com.lovebear.WebServer;

import java.io.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class data {
	
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
	
	public static void EditJson(){
		String filePath="F:\\myjavacode\\WebServer\\data.txt";
		String dataPath="F:\\myjavacode\\WebServer\\updateData.txt";
		
		File updateFile=new File(dataPath);
		FileWriter fw = null;
        BufferedWriter writer = null;
        
        sqlExecute se = new sqlExecute();
        
        readTxtFile(filePath);
		JSONObject  Json=JSONObject.fromObject(str);
		JSONObject  dataJson=JSONObject.fromObject(Json.get("result").toString());
		JSONArray data=JSONArray.fromObject(dataJson.get("data"));
		
        try{
	        fw = new FileWriter(updateFile);
	        writer = new BufferedWriter(fw);
			
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
				se.sqlAddData(o.get("title").toString(), o.get("date").toString(), o.get("author_name").toString(), o.get("thumbnail_pic_s").toString(), 
						o.get("thumbnail_pic_s02").toString(), o.get("thumbnail_pic_s03").toString(), 
						o.get("url").toString(), o.get("uniquekey").toString(), o.get("type").toString(), o.get("realtype").toString());
			}
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
	}

}
