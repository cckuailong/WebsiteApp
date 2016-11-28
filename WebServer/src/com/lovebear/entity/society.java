package com.lovebear.entity;
// default package



/**
 * society entity. @author MyEclipse Persistence Tools
 */

public class society  implements java.io.Serializable {


    // Fields    

     private societyId id;


    // Constructors

    /** default constructor */
    public society() {
    }

    
    /** full constructor */
    public society(societyId id) {
        this.id = id;
    }

   
    // Property accessors

    public societyId getId() {
        return this.id;
    }
    
    public void setId(societyId id) {
        this.id = id;
    }
   








}