package com.example.hasee.collage.activity.Bean;



/**
 * AppVersion entity. @author MyEclipse Persistence Tools
 */

public class AppVersion  implements java.io.Serializable {


    // Fields    

     private Integer verId;
     private Integer verCode;
     private String varName;
     private String verUrl;


    // Constructors

    /** default constructor */
    public AppVersion() {
    }

    
    /** full constructor */
    public AppVersion(Integer verCode, String varName, String verUrl) {
        this.verCode = verCode;
        this.varName = varName;
        this.verUrl = verUrl;
    }

   
    // Property accessors

    public Integer getVerId() {
        return this.verId;
    }
    
    public void setVerId(Integer verId) {
        this.verId = verId;
    }

    public Integer getVerCode() {
        return this.verCode;
    }
    
    public void setVerCode(Integer verCode) {
        this.verCode = verCode;
    }

    public String getVarName() {
        return this.varName;
    }
    
    public void setVarName(String varName) {
        this.varName = varName;
    }

    public String getVerUrl() {
        return this.verUrl;
    }
    
    public void setVerUrl(String verUrl){
        this.verUrl = verUrl;
    }
   








}