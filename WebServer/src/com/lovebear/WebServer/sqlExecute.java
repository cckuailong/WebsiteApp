package com.lovebear.WebServer;

import java.sql.ResultSet;  
import java.sql.SQLException;  
  
public class sqlExecute {  
    private String sql = null;  
    private DBHelper db1 = null;   
    
    public void sqlAddData(String title,String date,String author_name,
    		String thumbnail_pic_s,String thumbnail_pic_s02,String thumbnail_pic_s03,
    		String url,String uniquekey,String type,String realtype){
    
	    try {
	    	sql = "insert into data values("+"\""+title+"\",\""+date+"\",\""
	    		  +author_name+"\",\""+thumbnail_pic_s+"\",\""+thumbnail_pic_s02+"\",\""+thumbnail_pic_s03+"\",\""
	    		  +url+"\",\""+uniquekey+"\",\""+type+"\",\""+realtype+"\")"; 
	    	db1 = new DBHelper(sql); 
	    	System.out.println(sql);
	    	db1.pst.executeUpdate(); 

	        db1.close(); 
	    } catch (SQLException e) {
	    	   System.out.println("Failure");
	    	   e.printStackTrace();
	    }
    }
}
