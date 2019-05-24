package com.abrsoftware.speeddytest.model;

import java.io.Serializable;

public class Qoute implements Serializable {
    private String authorName;
    private String quote;
    private String img;

    public String getAuthor() {
        return authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
