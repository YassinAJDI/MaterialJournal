package com.ajdi.yassin.instajournal.data.source.local;

import android.support.annotation.NonNull;

import com.ajdi.yassin.instajournal.data.source.NotesDataSource;

/**
 * Concrete implementation of a data source as a db.
 */
public class NotesLocalDataSource implements NotesDataSource {
    @Override
    public void getNotes(@NonNull LoadNotesCallback callback) {

    }

    @Override
    public void getNote(String noteId, @NonNull GetNoteCallback callback) {

    }
}
