package com.chloe.greeneste;

public class GuideDTO {



    private String title;
    private String content;
    private String img;

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
}
