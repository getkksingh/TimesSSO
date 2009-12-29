package com.timesgroup.sso.hibernate.apis;

public class SecurityToken{
    private String salt;
    private String securitykey;
 
    public void setSalt(String salt){
          this.salt = salt;
    }
    public String getSalt(){
          return salt;
    }
    public void setSecuritykey(String securitykey){
          this.securitykey= securitykey;
    }
    public String getSecuritykey(){
          return securitykey;
    }
}