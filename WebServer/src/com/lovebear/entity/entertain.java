package com.lovebear.entity;
// default package



/**
 * entertain entity. @author MyEclipse Persistence Tools
 */

public class entertain  implements java.io.Serializable {


    // Fields    

     private entertainId id;


    // Constructors

    /** default constructor */
    public entertain() {
    }

    
    /** full constructor */
    public entertain(entertainId id) {
        this.id = id;
    }

   
    // Property accessors

    public entertainId getId() {
        return this.id;
    }
    
    public void setId(entertainId id) {
        this.id = id;
    }
   








}