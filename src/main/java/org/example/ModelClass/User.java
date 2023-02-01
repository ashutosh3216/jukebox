package org.example.ModelClass;

public class User {
    int userid;
    String username;
    String name;
    String passwords;
    String phoneNo;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public User( String username, String name, String phoneNo, String passwords) {
        this.username = username;
        this.name = name;
        this.passwords = passwords;
        this.phoneNo = phoneNo;
    }
}
