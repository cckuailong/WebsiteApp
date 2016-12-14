package com.example.hasee.collage.activity.Bean;


import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Tab entity. @author MyEclipse Persistence Tools
 */
@Table(name = "tab")
public class Tab  implements java.io.Serializable {


    // Fields    
    @Id(column = "tid")
    @NoAutoIncrement
     private Integer tid;

    public int getIsmy() {
        return ismy;
    }

    public void setIsmy(int ismy) {
        this.ismy = ismy;
    }

    @Column(column = "tab")

     private String tab;
    @Column(column = "ismy")
    private int ismy;

    // Constructors

    /** default constructor */
    public Tab() {
    }

    
    /** full constructor */
    public Tab(String tab) {
        this.tab = tab;
    }

   
    // Property accessors

    public Integer getTid() {
        return this.tid;
    }
    
    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTab() {
        return this.tab;
    }
    
    public void setTab(String tab) {
        this.tab = tab;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }
        if(obj instanceof Tab){
            if(this.tid == ((Tab)obj).getTid() && this.tab.equals(((Tab) obj).getTab())){
                return true;
            }
        }
        return false;
    }
}