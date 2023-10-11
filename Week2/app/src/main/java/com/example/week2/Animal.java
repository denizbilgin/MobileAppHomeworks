package com.example.week2;

public class Animal {
    private String type;
    private int picId;

    public Animal(String type, int picId) {
        this.type = type;
        this.picId = picId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }
}
