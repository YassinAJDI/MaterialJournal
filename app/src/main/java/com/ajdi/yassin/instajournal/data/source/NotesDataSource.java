package com.ajdi.yassin.instajournal.data.source;

import com.ajdi.yassin.instajournal.data.model.Note;

import java.util.List;

/**
 * Main entry point for accessing tasks data.
 */
public interface NotesDataSource {

    interface LoadNotesCallback {

        void onNotesLoaded(List<Note> notes);

        void onDataNotAvailable();
    }


}