package com.example.codevalley.recordListHelper;

import android.content.Context;

public class HelperClass_RecordList {
    int category, moneyAmount;
    String memo;



    public int getCategory(){
        return category;
    }

    public void setCategory(int category){
        this.category = category;
    }

    public String getMemo(){
        return memo;
    }

    public void setMemo(String memo){
        this.memo = memo;
    }

    public int getMoneyAmount(){
        return moneyAmount;
    }

    public void setMoneyAmount(){
        this.moneyAmount = moneyAmount;
    }

    public HelperClass_RecordList(String memo, int category, int moneyAmount){
        this.memo = memo;
        this.moneyAmount = moneyAmount;
        this.category = category;
    }

    public HelperClass_RecordList(){

    }

}
