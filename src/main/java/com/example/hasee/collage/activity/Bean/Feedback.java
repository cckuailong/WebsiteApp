package com.example.hasee.collage.activity.Bean;



/**
 * Feedback entity. @author MyEclipse Persistence Tools
 */

public class Feedback  implements java.io.Serializable {


    // Fields    

     private Integer fid;
     private String content;
     private String token;


    // Constructors

    /** default constructor */
    public Feedback() {
    }

    
    /** full constructor */
    public Feedback(String content, String token) {
        this.content = content;
        this.token = token;
    }

   
    // Property accessors

    public Integer getFid() {
        return this.fid;
    }
    
    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getToken() {
        return this.token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
   








}