package com.example.hasee.collage.activity.Bean;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by hasee on 2016/12/13.
 */
@Table(name = "savenews")
public class saveNews implements java.io.Serializable{
    @Id(column = "nid")
    private String nid;
    @Column(column = "title")
    private String title;
    @Column(column = "url")
    private String url;
    @Column(column = "picurl")
    private String picUrl;
    @Column(column = "account")
    private String account;

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public saveNews( String title, String url, String picUrl, String account) {
        this.title = title;
        this.url = url;
        this.picUrl = picUrl;
        this.account = account;
    }

    public saveNews() {

    }
}
