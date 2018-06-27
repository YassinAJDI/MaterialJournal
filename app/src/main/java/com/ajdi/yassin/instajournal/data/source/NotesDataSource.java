package com.ajdi.yassin.instajournal.data.source;

import android.support.annotation.NonNull;

import com.ajdi.yassin.instajournal.data.model.Note;
import com.ajdi.yassin.instajournal.ui.notedetail.NoteDetailViewModel;
import com.google.android.gms.tasks.Task;

import java.util.List;

/**
 * Main entry point for accessing tasks data.
 */
public interface NotesDataSource {

    interface LoadNotesCallback {

        void onNotesLoaded(List<Note> notes);

        void onDataNotAvailable();
    }

    interface GetNoteCallback {

        void onNoteLoaded(Note note);

        void onDataNotAvailable();
    }


    void getNotes(@NonNull LoadNotesCallback callback);

    void getNote(String noteId, @NonNull GetNoteCallback callback);

}