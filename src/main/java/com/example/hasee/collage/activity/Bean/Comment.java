package com.example.hasee.collage.activity.Bean;



/**
 * Comment entity. @author MyEclipse Persistence Tools
 */

public class Comment  implements java.io.Serializable {


    // Fields    

     private Integer cid;
     private Integer nid;
     private Integer uid;
     private String content;
     private Integer reply;
     private String cdate;


    // Constructors

    /** default constructor */
    public Comment() {
    }

	/** minimal constructor */
    public Comment(Integer nid, Integer uid, String content, String cdate) {
        this.nid = nid;
        this.uid = uid;
        this.content = content;
        this.cdate = cdate;
    }
    
    /** full constructor */
    public Comment(Integer nid, Integer uid, String content, Integer reply, String cdate) {
        this.nid = nid;
        this.uid = uid;
        this.content = content;
        this.reply = reply;
        this.cdate = cdate;
    }

   
    // Property accessors

    public Integer getCid() {
        return this.cid;
    }
    
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getNid() {
        return this.nid;
    }
    
    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public Integer getUid() {
        return this.uid;
    }
    
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReply() {
        return this.reply;
    }
    
    public void setReply(Integer reply) {
        this.reply = reply;
    }

    public String getCdate() {
        return this.cdate;
    }
    
    public void setCdate(String cdate) {
        this.cdate = cdate;
    }
   








}