package com.lovebear.entity;
// default package



/**
 * internation entity. @author MyEclipse Persistence Tools
 */

public class internation  implements java.io.Serializable {


    // Fields    

     private internationId id;


    // Constructors

    /** default constructor */
    public internation() {
    }

    
    /** full constructor */
    public internation(internationId id) {
        this.id = id;
    }

   
    // Property accessors

    public internationId getId() {
        return this.id;
    }
    
    public void setId(internationId id) {
        this.id = id;
    }
   








}