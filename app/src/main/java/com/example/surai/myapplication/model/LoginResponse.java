package com.example.surai.myapplication.model;

public class LoginResponse {

    private int id;
    private String token;
    private Status status;

    public LoginResponse(int id, String token, Status status) {
        this.id = id;
        this.token = token.trim();
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public Status getStatus() {
        return status;
    }
}