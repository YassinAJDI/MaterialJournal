package com.ajdi.yassin.instajournal.data.model;

import com.google.common.base.Strings;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "note")
public class Note {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "content")
    private String mContent;

    @ColumnInfo(name = "date")
    private long mDate;

    /**
     * This constructor is used by Room.
     */
    public Note(int id, String title, String content, long date) {
        this.mId = id;
        this.mTitle = title;
        this.mContent = content;
        this.mDate = date;
    }

    @Ignore
    public Note(String title, String content, long date) {
        this.mTitle = title;
        this.mContent = content;
        this.mDate = date;
    }

    public int getId() {
        return mId;
    }

    public long getDate() {
        return mDate;
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

    public boolean isEmpty() {
        return Strings.isNullOrEmpty(mTitle) || Strings.isNullOrEmpty(mContent);
    }
}
