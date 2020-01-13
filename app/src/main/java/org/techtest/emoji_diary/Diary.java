package org.techtest.emoji_diary;

import java.util.Date;

public class Diary {

    private int emoji;
    private String title;
    private Date date;
    private String content;
    private boolean favorite;

    Diary(){ }

    public Diary(int emoji, String title, Date date, String content, boolean favorite){
        this.emoji = emoji;
        this.title = title;
        this.date = date;
        this.content = content;
        this.favorite = favorite;
    }

    public void setImage(int emoji){
        this.emoji = emoji;
    }

    public int getImage(){
        return emoji;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate(){
        return date;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }

    public void setFavorite(boolean favorite){
        this.favorite = favorite;
    }

    public boolean getFavorite(){
        return favorite;
    }

}
