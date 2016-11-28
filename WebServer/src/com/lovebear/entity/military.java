package com.lovebear.entity;
// default package



/**
 * military entity. @author MyEclipse Persistence Tools
 */

public class military  implements java.io.Serializable {


    // Fields    

     private militaryId id;


    // Constructors

    /** default constructor */
    public military() {
    }

    
    /** full constructor */
    public military(militaryId id) {
        this.id = id;
    }

   
    // Property accessors

    public militaryId getId() {
        return this.id;
    }
    
    public void setId(militaryId id) {
        this.id = id;
    }
   








}