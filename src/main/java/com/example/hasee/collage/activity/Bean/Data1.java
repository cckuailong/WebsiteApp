package com.example.hasee.collage.activity.Bean;
// default package


import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.Table;

/**
 * DataId entity. @author MyEclipse Persistence Tools
 */
@Table(name = "data1")
public class Data1  implements java.io.Serializable {


    // Fields
    @Column(column = "so")
    private String source;
    @Column(column = "ti")
    private String title;
    @Column(column = "da")
    private String date;
    @Column(column = "au")
    private String authorName;
    @Column(column = "th")
    private String thumbnailPicS;
    @Column(column = "ur")
    private String url;
    @Id(column = "id")
    private int Nid;


    // Constructors

    public int getNid() {
        return Nid;
    }

    public void setNid(int nid) {
        Nid = nid;
    }

    /** default constructor */
    public Data1() {
    }


    /** full constructor */
    public Data1(String source,String title, String date, String authorName, String thumbnailPicS, String url) {
        this.source=source;
        this.title = title;
        this.date = date;
        this.authorName = authorName;
        this.thumbnailPicS = thumbnailPicS;
        this.url = url;
    }


    // Property accessors

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getThumbnailPicS() {
        return this.thumbnailPicS;
    }

    public void setThumbnailPicS(String thumbnailPicS) {
        this.thumbnailPicS = thumbnailPicS;
    }



    public String getSource() {
        return source;
    }


    public void setSource(String source) {
        this.source = source;
    }


    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( (other == null ) ) return false;
        if ( !(other instanceof DataId) ) return false;
        DataId castOther = ( DataId ) other;

        return ( (this.getTitle()==castOther.getTitle()) || ( this.getTitle()!=null && castOther.getTitle()!=null && this.getTitle().equals(castOther.getTitle()) ) )
                && ( (this.getDate()==castOther.getDate()) || ( this.getDate()!=null && castOther.getDate()!=null && this.getDate().equals(castOther.getDate()) ) )
                && ( (this.getAuthorName()==castOther.getAuthorName()) || ( this.getAuthorName()!=null && castOther.getAuthorName()!=null && this.getAuthorName().equals(castOther.getAuthorName()) ) )
                && ( (this.getThumbnailPicS()==castOther.getThumbnailPicS()) || ( this.getThumbnailPicS()!=null && castOther.getThumbnailPicS()!=null && this.getThumbnailPicS().equals(castOther.getThumbnailPicS()) ) )
                && ( (this.getSource()==castOther.getSource()) || ( this.getSource()!=null && castOther.getSource()!=null && this.getSource().equals(castOther.getSource()) ) )
                && ( (this.getUrl()==castOther.getUrl()) || ( this.getUrl()!=null && castOther.getUrl()!=null && this.getUrl().equals(castOther.getUrl()) ) );

    }



}