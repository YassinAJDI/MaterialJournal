package com.ajdi.yassin.materialjournal.ui.notes;

import com.ajdi.yassin.materialjournal.data.model.Note;

/**
 * Listener used with data binding to process user actions.
 */
public interface NoteItemUserActionsListener {

    void onNoteClicked(Note note);
}
