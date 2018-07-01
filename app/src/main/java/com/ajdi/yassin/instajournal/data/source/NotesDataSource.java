package com.ajdi.yassin.instajournal.data.source;

import com.ajdi.yassin.instajournal.data.model.Note;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Main entry point for accessing tasks data.
 */
public interface NotesDataSource {

    void starNote(@NonNull Note note);

    interface LoadNotesCallback {

        void onNotesLoaded(List<Note> notes);

        void onDataNotAvailable();
    }

    interface GetNoteCallback {

        void onNoteLoaded(Note note);

        void onDataNotAvailable();
    }


    void getNotes(@NonNull LoadNotesCallback callback);

    void getNote(@NonNull String noteId, @NonNull GetNoteCallback callback);

    void saveNote(@NonNull Note note);

}