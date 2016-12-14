package com.example.hasee.collage.activity.Bean;



/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role  implements java.io.Serializable {


    // Fields    

     private Integer rid;
     private String role;


    // Constructors

    /** default constructor */
    public Role() {
    }

    
    /** full constructor */
    public Role(String role) {
        this.role = role;
    }

   
    // Property accessors

    public Integer getRid() {
        return this.rid;
    }
    
    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
   








}