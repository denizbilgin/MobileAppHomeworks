package com.example.week9;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {
    private String filePath;
    private Date date;
    private String header;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
