package com.lovebear.entity;
// default package



/**
 * DataId entity. @author MyEclipse Persistence Tools
 */

public class DataId  implements java.io.Serializable {


    // Fields    

     private String title;
     private String date;
     private String authorName;
     private String thumbnailPicS;
     private String thumbnailPicS02;
     private String thumbnailPicS03;
     private String url;
     private String uniquekey;
     private String type;
     private String realtype;


    // Constructors

    /** default constructor */
    public DataId() {
    }

    
    /** full constructor */
    public DataId(String title, String date, String authorName, String thumbnailPicS, String thumbnailPicS02, String thumbnailPicS03, String url, String uniquekey, String type, String realtype) {
        this.title = title;
        this.date = date;
        this.authorName = authorName;
        this.thumbnailPicS = thumbnailPicS;
        this.thumbnailPicS02 = thumbnailPicS02;
        this.thumbnailPicS03 = thumbnailPicS03;
        this.url = url;
        this.uniquekey = uniquekey;
        this.type = type;
        this.realtype = realtype;
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

    public String getThumbnailPicS02() {
        return this.thumbnailPicS02;
    }
    
    public void setThumbnailPicS02(String thumbnailPicS02) {
        this.thumbnailPicS02 = thumbnailPicS02;
    }

    public String getThumbnailPicS03() {
        return this.thumbnailPicS03;
    }
    
    public void setThumbnailPicS03(String thumbnailPicS03) {
        this.thumbnailPicS03 = thumbnailPicS03;
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

    public String getRealtype() {
        return this.realtype;
    }
    
    public void setRealtype(String realtype) {
        this.realtype = realtype;
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
 && ( (this.getThumbnailPicS02()==castOther.getThumbnailPicS02()) || ( this.getThumbnailPicS02()!=null && castOther.getThumbnailPicS02()!=null && this.getThumbnailPicS02().equals(castOther.getThumbnailPicS02()) ) )
 && ( (this.getThumbnailPicS03()==castOther.getThumbnailPicS03()) || ( this.getThumbnailPicS03()!=null && castOther.getThumbnailPicS03()!=null && this.getThumbnailPicS03().equals(castOther.getThumbnailPicS03()) ) )
 && ( (this.getUrl()==castOther.getUrl()) || ( this.getUrl()!=null && castOther.getUrl()!=null && this.getUrl().equals(castOther.getUrl()) ) )
 && ( (this.getUniquekey()==castOther.getUniquekey()) || ( this.getUniquekey()!=null && castOther.getUniquekey()!=null && this.getUniquekey().equals(castOther.getUniquekey()) ) )
 && ( (this.getType()==castOther.getType()) || ( this.getType()!=null && castOther.getType()!=null && this.getType().equals(castOther.getType()) ) )
 && ( (this.getRealtype()==castOther.getRealtype()) || ( this.getRealtype()!=null && castOther.getRealtype()!=null && this.getRealtype().equals(castOther.getRealtype()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTitle() == null ? 0 : this.getTitle().hashCode() );
         result = 37 * result + ( getDate() == null ? 0 : this.getDate().hashCode() );
         result = 37 * result + ( getAuthorName() == null ? 0 : this.getAuthorName().hashCode() );
         result = 37 * result + ( getThumbnailPicS() == null ? 0 : this.getThumbnailPicS().hashCode() );
         result = 37 * result + ( getThumbnailPicS02() == null ? 0 : this.getThumbnailPicS02().hashCode() );
         result = 37 * result + ( getThumbnailPicS03() == null ? 0 : this.getThumbnailPicS03().hashCode() );
         result = 37 * result + ( getUrl() == null ? 0 : this.getUrl().hashCode() );
         result = 37 * result + ( getUniquekey() == null ? 0 : this.getUniquekey().hashCode() );
         result = 37 * result + ( getType() == null ? 0 : this.getType().hashCode() );
         result = 37 * result + ( getRealtype() == null ? 0 : this.getRealtype().hashCode() );
         return result;
   }   





}