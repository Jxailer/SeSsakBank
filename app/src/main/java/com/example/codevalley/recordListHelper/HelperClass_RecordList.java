package com.example.codevalley.recordListHelper;

import android.content.Context;

public class HelperClass_RecordList {
    private String memo;
    private String category;
    private String moneyAmount;



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

    public HelperClass_RecordList(String memo, String category, String moneyAmount){
        this.memo = memo;
        this.moneyAmount = moneyAmount;
        this.category = category;
    }

    public HelperClass_RecordList(){

    }

}
