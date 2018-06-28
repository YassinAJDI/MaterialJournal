package com.ajdi.yassin.instajournal.ui.notes;

import com.ajdi.yassin.instajournal.data.model.Note;

/**
 * Listener used with data binding to process user actions.
 */
public interface NoteItemUserActionsListener {

    void onNoteClicked(Note note);
}
