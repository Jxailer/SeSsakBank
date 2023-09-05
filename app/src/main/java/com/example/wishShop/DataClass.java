package com.example.wishShop;

public class DataClass {

    private String dataTitle;
    private String dataStamp;
    private String dataDesc;

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
}
