package com.ajdi.yassin.materialJournal.data.model;

import com.google.common.base.Strings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    @ColumnInfo(name = "star")
    private boolean isStar;

    @ColumnInfo(name = "trash")
    private boolean isTrash;

    /**
     * This constructor is used by Room.
     */
    public Note(int id, String title, String content, long date, boolean isStar, boolean isTrash) {
        this.mId = id;
        this.mTitle = title;
        this.mContent = content;
        this.mDate = date;
        this.isStar = isStar;
        this.isTrash = isTrash;
    }

    @Ignore
    public Note(String title, String content, long date) {
        this.mTitle = title;
        this.mContent = content;
        this.mDate = date;
    }

    /**
     * Use this constructor to specify a stared Note if the note already has an id (copy of
     * another note).
     */
    @Ignore
    public Note(@Nullable Note note, boolean isStar) {
        mId = note.mId;
        mTitle = note.mTitle;
        mContent = note.mContent;
        mDate = note.getDate();
        this.isStar = isStar;
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

    public boolean isStar() {
        return isStar;
    }

    public void setStar(boolean star) {
        isStar = star;
    }

    public boolean isTrash() {
        return isTrash;
    }

    public void setTrash(boolean trash) {
        isTrash = trash;
    }

    public boolean isEmpty() {
        return Strings.isNullOrEmpty(mTitle) || Strings.isNullOrEmpty(mContent);
    }
}
