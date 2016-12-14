package com.example.hasee.collage.activity.Bean;
// default package


import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.Table;

/**
 * DataId entity. @author MyEclipse Persistence Tools
 */
@Table(name = "dataid")
public class DataId  implements java.io.Serializable {


    // Fields    
    @Column(column = "source")
    private String source;
    @Column(column = "title")
    private String title;
    @Column(column = "date")
    private String date;
    @Column(column = "authorName")
    private String authorName;
    @Column(column = "thumbnailPicS")
    private String thumbnailPicS;
    @Column(column = "url")
    private String url;
    @Column(column = "uniquekey")
    private String uniquekey;
    @Column(column = "type")
    private String type;
    @Id(column = "Nid")
    private int Nid;


    // Constructors

    public int getNid() {
        return Nid;
    }

    public void setNid(int nid) {
        Nid = nid;
    }

    /** default constructor */
    public DataId() {
    }

    
    /** full constructor */
    public DataId(String source,String title, String date, String authorName, String thumbnailPicS, String url, String uniquekey, String type, String realtype) {
        this.source=source;
    	this.title = title;
        this.date = date;
        this.authorName = authorName;
        this.thumbnailPicS = thumbnailPicS;
        this.url = url;
        this.uniquekey = uniquekey;
        this.type = type;
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

    public String getUniquekey() {
        return this.uniquekey;
    }
    
    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
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
 && ( (this.getUrl()==castOther.getUrl()) || ( this.getUrl()!=null && castOther.getUrl()!=null && this.getUrl().equals(castOther.getUrl()) ) )
 && ( (this.getUniquekey()==castOther.getUniquekey()) || ( this.getUniquekey()!=null && castOther.getUniquekey()!=null && this.getUniquekey().equals(castOther.getUniquekey()) ) )
 && ( (this.getType()==castOther.getType()) || ( this.getType()!=null && castOther.getType()!=null && this.getType().equals(castOther.getType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTitle() == null ? 0 : this.getTitle().hashCode() );
         result = 37 * result + ( getDate() == null ? 0 : this.getDate().hashCode() );
         result = 37 * result + ( getAuthorName() == null ? 0 : this.getAuthorName().hashCode() );
         result = 37 * result + ( getThumbnailPicS() == null ? 0 : this.getThumbnailPicS().hashCode() );
         result = 37 * result + ( getSource() == null ? 0 : this.getSource().hashCode() );
         result = 37 * result + ( getUrl() == null ? 0 : this.getUrl().hashCode() );
         result = 37 * result + ( getUniquekey() == null ? 0 : this.getUniquekey().hashCode() );
         result = 37 * result + ( getType() == null ? 0 : this.getType().hashCode() );
         return result;
   }   





}