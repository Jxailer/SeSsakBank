package com.example.post_Adult;

public class BoardWrite {

    private String user_key;
    private String user_title;  // 글제목
    private String user_text;   // 글내용

    public BoardWrite(){

    }

    public BoardWrite(String user_key, String user_title, String user_text) {
        this.user_key = user_key;
        this.user_title = user_title;
        this.user_text = user_text;
    }

    public String getUser_key() {
        return user_key;
    }

    public void setUser_key(String user_key) {
        this.user_key = user_key;
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
