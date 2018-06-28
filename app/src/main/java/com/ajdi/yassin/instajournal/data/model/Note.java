package com.ajdi.yassin.instajournal.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "note")
public class Note {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private String mId;

    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "content")
    private String mContent;

    @ColumnInfo(name = "date")
    private long mDate;

    /**
     * This constructor is used by Room.
     */
    public Note(@NonNull String id, String title, String content, long date) {
        this.mId = id;
        this.mTitle = title;
        this.mContent = content;
        this.mDate = date;
    }

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
