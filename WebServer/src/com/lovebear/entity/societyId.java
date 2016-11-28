package com.lovebear.entity;
// default package



/**
 * societyId entity. @author MyEclipse Persistence Tools
 */

public class societyId  implements java.io.Serializable {


    // Fields    

     private String title;
     private String date;
     private String authorName;
     private String thumbnailPicS;
     private String url;
     private String uniquekey;
     private String type;
     private String source;


    // Constructors

    /** default constructor */
    public societyId() {
    }

    
    /** full constructor */
    public societyId(String title, String date, String authorName, String thumbnailPicS, String url, String uniquekey, String type, String source) {
        this.title = title;
        this.date = date;
        this.authorName = authorName;
        this.thumbnailPicS = thumbnailPicS;
        this.url = url;
        this.uniquekey = uniquekey;
        this.type = type;
        this.source = source;
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

    public String getSource() {
        return this.source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof societyId) ) return false;
		 societyId castOther = ( societyId ) other; 
         
		 return ( (this.getTitle()==castOther.getTitle()) || ( this.getTitle()!=null && castOther.getTitle()!=null && this.getTitle().equals(castOther.getTitle()) ) )
 && ( (this.getDate()==castOther.getDate()) || ( this.getDate()!=null && castOther.getDate()!=null && this.getDate().equals(castOther.getDate()) ) )
 && ( (this.getAuthorName()==castOther.getAuthorName()) || ( this.getAuthorName()!=null && castOther.getAuthorName()!=null && this.getAuthorName().equals(castOther.getAuthorName()) ) )
 && ( (this.getThumbnailPicS()==castOther.getThumbnailPicS()) || ( this.getThumbnailPicS()!=null && castOther.getThumbnailPicS()!=null && this.getThumbnailPicS().equals(castOther.getThumbnailPicS()) ) )
 && ( (this.getUrl()==castOther.getUrl()) || ( this.getUrl()!=null && castOther.getUrl()!=null && this.getUrl().equals(castOther.getUrl()) ) )
 && ( (this.getUniquekey()==castOther.getUniquekey()) || ( this.getUniquekey()!=null && castOther.getUniquekey()!=null && this.getUniquekey().equals(castOther.getUniquekey()) ) )
 && ( (this.getType()==castOther.getType()) || ( this.getType()!=null && castOther.getType()!=null && this.getType().equals(castOther.getType()) ) )
 && ( (this.getSource()==castOther.getSource()) || ( this.getSource()!=null && castOther.getSource()!=null && this.getSource().equals(castOther.getSource()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTitle() == null ? 0 : this.getTitle().hashCode() );
         result = 37 * result + ( getDate() == null ? 0 : this.getDate().hashCode() );
         result = 37 * result + ( getAuthorName() == null ? 0 : this.getAuthorName().hashCode() );
         result = 37 * result + ( getThumbnailPicS() == null ? 0 : this.getThumbnailPicS().hashCode() );
         result = 37 * result + ( getUrl() == null ? 0 : this.getUrl().hashCode() );
         result = 37 * result + ( getUniquekey() == null ? 0 : this.getUniquekey().hashCode() );
         result = 37 * result + ( getType() == null ? 0 : this.getType().hashCode() );
         result = 37 * result + ( getSource() == null ? 0 : this.getSource().hashCode() );
         return result;
   }   





}