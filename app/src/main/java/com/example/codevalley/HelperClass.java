package com.example.codevalley;

public class HelperClass {

    String username, password, name, birth, phone, adult_name, adult_nickname;

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

    public String getAdult_name() { return adult_name; }
    public void setAdult_name(String adult_name) { this.adult_name = adult_name; }

    public String getAdult_nickname() { return adult_nickname; }
    public void setAdult_nickname(String adult_nickname) { this.adult_nickname = adult_nickname; }


    public HelperClass(String username, String password, String name, String birth, String phone, String result) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
    }

    public HelperClass(String username, String adult_nickname, String adult_name){
        this.username = username;
        this.adult_nickname = adult_nickname;
        this.adult_name = adult_name;
    }
}
