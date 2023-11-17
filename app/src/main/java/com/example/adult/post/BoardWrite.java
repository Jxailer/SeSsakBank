package com.example.adult.post;

public class BoardWrite {

    private String user_nick;
    private String user_time;
    private String user_title;  // 글제목
    private String user_text;   // 글내용
    private String user_key;

    public BoardWrite(){

    }

    public BoardWrite(String user_nick, String user_time, String user_title, String user_text) {
        this.user_nick = user_nick;
        this.user_time = user_time;
        this.user_title = user_title;
        this.user_text = user_text;
    }

    public String getUser_key() { return user_key; }
    public void setUser_key(String user_key) {
        this.user_key = user_key;
    }
    public String getUser_nick() {
        return user_nick;
    }

    public void setUser_nick(String user_nick) {
        this.user_nick = user_nick;
    }

    public String getUser_time(){
        return user_time;
    }
    public void setUser_time(String user_time){
        this.user_time = user_time;
    }

    public String getUser_title() {
        return user_title;
    }

    public void setUser_title(String user_title) {
        this.user_title = user_title;
    }

    public String getUser_text() {
        return user_text;
    }

    public void setUser_text(String user_text) {
        this.user_text = user_text;
    }
}