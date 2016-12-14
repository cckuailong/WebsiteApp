package com.lovebear.spider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FengHSpider {

private String str;

private ArrayList<String> fhList=new ArrayList<String>();

public ArrayList<String> getFhList() {
	return fhList;
}

public void setFhList(ArrayList<String> fhList) {
	this.fhList = fhList;
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

	public void getAllJason(){
		String strEnt,strAll;
		try {
			Spider.downloadPage("http://news.ifeng.com/","/data/fenghuang/FHdata.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		readTxtFile("/data/fenghuang/FHdata.txt");
		
		strEnt=str.substring(str.indexOf("[{\""),str.indexOf("\"}]")+3);
		strAll=str.substring(str.indexOf("[[{\"")+2,str.indexOf("\"}]]")+2);
		String[] tmpList=strAll.split("\\],\\[");
		for(String s:tmpList){
			fhList.add('['+s+']');
		}
		fhList.add(strEnt);

	}

}