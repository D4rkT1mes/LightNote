package com.nanasdev.lightnote;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {
    private Date date;
    private String header;
    private String body;

    public Note() {
    }

    public Note(Date date, String header, String body) {
        this.date = date;
        this.header = header;
        this.body = body;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
