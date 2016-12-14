package com.lovebear.entity;
// default package



/**
 * UserId entity. @author MyEclipse Persistence Tools
 */

public class UserId  implements java.io.Serializable {


    // Fields    

     private String uid;
     private String phone;
     private String pwd;
     private String email;
     private String token;
     private String gender;
     private String nickname;


    // Constructors

    /** default constructor */
    public UserId() {
    }

    
    /** full constructor */
    public UserId(String uid, String phone, String pwd, String email, String token,String gender,String nickname) {
        this.uid = uid;
        this.phone = phone;
        this.pwd = pwd;
        this.email = email;
        this.token = token;
        this.gender=gender;
        this.nickname=nickname;
    }

   
    // Property accessors

    public String getUid() {
        return this.uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return this.pwd;
    }
    
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return this.token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }

   public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UserId) ) return false;
		 UserId castOther = ( UserId ) other; 
         
		 return ( (this.getUid()==castOther.getUid()) || ( this.getUid()!=null && castOther.getUid()!=null && this.getUid().equals(castOther.getUid()) ) )
 && ( (this.getPhone()==castOther.getPhone()) || ( this.getPhone()!=null && castOther.getPhone()!=null && this.getPhone().equals(castOther.getPhone()) ) )
 && ( (this.getPwd()==castOther.getPwd()) || ( this.getPwd()!=null && castOther.getPwd()!=null && this.getPwd().equals(castOther.getPwd()) ) )
 && ( (this.getEmail()==castOther.getEmail()) || ( this.getEmail()!=null && castOther.getEmail()!=null && this.getEmail().equals(castOther.getEmail()) ) )
 && ( (this.getToken()==castOther.getToken()) || ( this.getToken()!=null && castOther.getToken()!=null && this.getToken().equals(castOther.getToken()) ) )
 && ( (this.getGender()==castOther.getGender()) || ( this.getGender()!=null && castOther.getGender()!=null && this.getGender().equals(castOther.getGender()) ) )
 && ( (this.getNickname()==castOther.getNickname()) || ( this.getNickname()!=null && castOther.getNickname()!=null && this.getNickname().equals(castOther.getNickname()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUid() == null ? 0 : this.getUid().hashCode() );
         result = 37 * result + ( getPhone() == null ? 0 : this.getPhone().hashCode() );
         result = 37 * result + ( getPwd() == null ? 0 : this.getPwd().hashCode() );
         result = 37 * result + ( getEmail() == null ? 0 : this.getEmail().hashCode() );
         result = 37 * result + ( getToken() == null ? 0 : this.getToken().hashCode() );
         result = 37 * result + ( getGender() == null ? 0 : this.getGender().hashCode() );
         result = 37 * result + ( getNickname() == null ? 0 : this.getNickname().hashCode() );
         return result;
   }   





}