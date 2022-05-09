package com.chloe.greeneste;

import java.util.ArrayList;

public class MyUser {

    public String username ;
    public String useremail;
    public String userpw;
    public String uid;
    public String birthday;
    public int pointtotal;

    public MyUser(){}
     public MyUser(String useremail, String username, String userpw, String uid, String birthday, int pointtotal){
        this.useremail = useremail;
        this.userpw = userpw;
        this.username= username;
        this.uid = uid;
        this.birthday = birthday;
        this.pointtotal = pointtotal;
    }

}
