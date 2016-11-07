package com.lovebear.entity;
// default package



/**
 * Data entity. @author MyEclipse Persistence Tools
 */

public class Data  implements java.io.Serializable {


    // Fields    

     private DataId id;


    // Constructors

    /** default constructor */
    public Data() {
    }

    
    /** full constructor */
    public Data(DataId id) {
        this.id = id;
    }

   
    // Property accessors

    public DataId getId() {
        return this.id;
    }
    
    public void setId(DataId id) {
        this.id = id;
    }
   








}