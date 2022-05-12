package com.chloe.greeneste;

import java.io.Serializable;

public class GuideDTO implements Serializable {



    private String title;
    private String content;
    private String img;
    public String key;
    public GuideDTO(){}

    public GuideDTO(String title, String content, String img){
        this.title = title;
        this.content = content;
        this.img = img;

    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String toString(){
        return getTitle() + ": " + getContent();
    }

    public String getThumbContent() {
        if (this.content.length() <= 20){
            return this.content;
        }
        return this.content.substring(0,20);
    }
}
