package com.ajdi.yassin.instajournal.data.model;

public class Note {

    private String mTitle;
    private String mContent;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        this.mContent = content;
    }

    public String toString() {
        return "{Note title=" + mTitle + " content=" + mContent + "}";
    }
}
