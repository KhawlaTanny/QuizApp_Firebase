package com.khawlat.quizapp_c;

public class User {
    public String fullname, email, password, score;

    public User() {

    }

    public User(String fullname, String email, String password) {
        this.fullname = fullname;
        this.score = "0";
        this.email = email;
        this.password = password;
    }
}