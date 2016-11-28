package com.lovebear.entity;
// default package



/**
 * sport entity. @author MyEclipse Persistence Tools
 */

public class sport  implements java.io.Serializable {


    // Fields    

     private sportId id;


    // Constructors

    /** default constructor */
    public sport() {
    }

    
    /** full constructor */
    public sport(sportId id) {
        this.id = id;
    }

   
    // Property accessors

    public sportId getId() {
        return this.id;
    }
    
    public void setId(sportId id) {
        this.id = id;
    }
   








}