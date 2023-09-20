package com.example.wishShop;

public class DataClass {

    private String dataTitle;
    private String dataStamp;
    private String dataDesc;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataStamp() {
        return dataStamp;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public DataClass(String dataTitle, String dataStamp, String dataDesc) {
        this.dataTitle = dataTitle;
        this.dataStamp = dataStamp;
        this.dataDesc = dataDesc;
    }

    public DataClass(){

    }
}
