package com.example.codevalley.recordListHelper;

import android.content.Context;

public class HelperClass_RecordList {
    private String memo;
    private String category;
    private String moneyAmount;
    private String pm;
    public String date;


    public HelperClass_RecordList(){

    }


    public HelperClass_RecordList(String memo, String category, String moneyAmount, String pm, String date){
        this.category = category;
        this.date = date;
        this.memo = memo;
        this.moneyAmount = moneyAmount;
        this.pm = pm;

    }




    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getMemo(){
        return memo;
    }

    public void setMemo(String memo){
        this.memo = memo;
    }

    public String getMoneyAmount(){
        return moneyAmount;
    }

    public void setMoneyAmount(){
        this.moneyAmount = moneyAmount;
    }
    public String getPm(){
        return pm;
    }

    public void setPm(String pm){
        this.pm = pm;
    }
    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }


}
