package com.lovebear.entity;
// default package



/**
 * finance entity. @author MyEclipse Persistence Tools
 */

public class finance  implements java.io.Serializable {


    // Fields    

     private financeId id;


    // Constructors

    /** default constructor */
    public finance() {
    }

    
    /** full constructor */
    public finance(financeId id) {
        this.id = id;
    }

   
    // Property accessors

    public financeId getId() {
        return this.id;
    }
    
    public void setId(financeId id) {
        this.id = id;
    }
   








}