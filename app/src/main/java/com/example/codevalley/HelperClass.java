package com.example.codevalley;

import androidx.appcompat.widget.ThemedSpinnerAdapter;

public class HelperClass {

    String username, password, name, birth, phone, adult_name, adult_nickname, createDate;
    Integer fertilizer, synthesis, water, stamp;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() { return birth; }
    public void setBirth(String birth) { this.birth = birth;}

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAdult_name() { return adult_name; }
    public void setAdult_name(String adult_name) { this.adult_name = adult_name; }

    public String getAdult_nickname(){ return adult_nickname; }
    public void setAdult_nickname(String adult_nickname) { this.adult_nickname = adult_nickname; }

    public Integer getFertilizer() { return fertilizer; }
    public void setFertilizer(Integer fertilizer) { this.fertilizer = fertilizer; }

    public Integer getSynthesis() { return synthesis; }
    public void setSynthesis(Integer synthesis) { this.synthesis = synthesis; }

    public Integer getWater() { return water; }
    public void setWater(Integer water) { this.water = water; }

    public Integer getStamp() { return stamp; }
    public void setStamp(Integer stamp) { this.stamp = stamp; }

    public HelperClass(){

    }

    public HelperClass(String username, String password, String name, String birth, String phone, String createDate, Integer stamp) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.createDate = createDate;
        this.stamp = stamp;
    }

    public HelperClass(String adult_nickname, String adult_name){
        this.adult_nickname = adult_nickname;
        this.adult_name = adult_name;
    }

    public HelperClass(Integer fertilizer, Integer synthesis, Integer water){
        this.fertilizer = fertilizer;
        this.synthesis = synthesis;
        this.water = water;
    }
}
