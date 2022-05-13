package com.chloe.greeneste;

import java.util.ArrayList;

public class MyUser {

    public String username ;
    public String useremail;
    public String uid;
    public String phone;
    public String birthday;
    public int pointtotal;

    public MyUser(){}
     public MyUser(String useremail, String username, String uid, String phone, String birthday, int pointtotal){
        this.useremail = useremail;
        this.username= username;
        this.uid = uid;
        this.phone=phone;
        this.birthday = birthday;
        this.pointtotal = pointtotal;
    }

}
