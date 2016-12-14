package com.example.hasee.collage.activity.Bean;



/**
 * News entity. @author MyEclipse Persistence Tools
 */

public class News  implements java.io.Serializable {


    // Fields    

     private Integer nid;
     private String title;
     private String thumbUrl;
     private String contentUrl;
     private String summary;
     private String contentType;
     private Integer tabid;
     private String author;
     private String issueDate;


    // Constructors

    /** default constructor */
    public News() {
    }

	/** minimal constructor */
    public News(String title, String contentUrl, String summary, String contentType, Integer tabid, String author, String issueDate) {
        this.title = title;
        this.contentUrl = contentUrl;
        this.summary = summary;
        this.contentType = contentType;
        this.tabid = tabid;
        this.author = author;
        this.issueDate = issueDate;
    }
    
    /** full constructor */
    public News(String title, String thumbUrl, String contentUrl, String summary, String contentType, Integer tabid, String author, String issueDate) {
        this.title = title;
        this.thumbUrl = thumbUrl;
        this.contentUrl = contentUrl;
        this.summary = summary;
        this.contentType = contentType;
        this.tabid = tabid;
        this.author = author;
        this.issueDate = issueDate;
    }

   
    // Property accessors

    public Integer getNid() {
        return this.nid;
    }
    
    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbUrl() {
        return this.thumbUrl;
    }
    
    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getContentUrl() {
        return this.contentUrl;
    }
    
    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getSummary() {
        return this.summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContentType() {
        return this.contentType;
    }
    
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getTabid() {
        return this.tabid;
    }
    
    public void setTabid(Integer tabid) {
        this.tabid = tabid;
    }

    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIssueDate() {
        return this.issueDate;
    }
    
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
   








}