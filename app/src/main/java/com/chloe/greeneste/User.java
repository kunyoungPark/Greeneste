package com.chloe.greeneste;

import java.util.ArrayList;

public class User {

    public String username ;
    public String useremail;
    public String userpw;
    public String uid;
    public String birthday;
    public int pointtotal;

    User(){}
    public User(String useremail,String username, String userpw, String uid, String birthday, int pointtotal){
        this.useremail = useremail;
        this.userpw = userpw;
        this.username= username;
        this.uid = uid;
        this.birthday = birthday;
        this.pointtotal = pointtotal;
    }

}
