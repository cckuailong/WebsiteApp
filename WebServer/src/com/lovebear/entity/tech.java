package com.lovebear.entity;
// default package



/**
 * tech entity. @author MyEclipse Persistence Tools
 */

public class tech  implements java.io.Serializable {


    // Fields    

     private techId id;


    // Constructors

    /** default constructor */
    public tech() {
    }

    
    /** full constructor */
    public tech(techId id) {
        this.id = id;
    }

   
    // Property accessors

    public techId getId() {
        return this.id;
    }
    
    public void setId(techId id) {
        this.id = id;
    }
   








}