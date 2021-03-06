package com.ajdi.yassin.materialjournal.ui.addedit;

import android.app.Application;

import com.ajdi.yassin.materialjournal.R;
import com.ajdi.yassin.materialjournal.data.model.Note;
import com.ajdi.yassin.materialjournal.data.source.NotesDataSource;
import com.ajdi.yassin.materialjournal.data.source.NotesRepository;
import com.ajdi.yassin.materialjournal.utils.SingleLiveEvent;
import com.ajdi.yassin.materialjournal.utils.SnackbarMessage;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;

/**
 * ViewModel for the Add/Edit screen.
 */
public class AddEditNoteViewModel extends AndroidViewModel implements NotesDataSource.GetNoteCallback {

    public final ObservableField<String> title = new ObservableField<>();

    public final ObservableField<String> content = new ObservableField<>();

    public final ObservableField<Note> note = new ObservableField<>();

    public final ObservableBoolean dataLoading = new ObservableBoolean(false);

    private final SingleLiveEvent<Void> mNoteUpdated = new SingleLiveEvent<>();

    private final SnackbarMessage mSnackbarText = new SnackbarMessage();

    private final NotesRepository mNotesRepository;

    @Nullable
    private String mNoteId;

    private boolean mIsNewNote;

    private boolean mIsDataLoaded = false;

    public AddEditNoteViewModel(@NonNull Application context, NotesRepository notesRepository) {
        super(context);
        mNotesRepository = notesRepository;
    }

    public void start(String noteId) {
        if (dataLoading.get()) {
            // Already loading, ignore.
            return;
        }
        mNoteId = noteId;
        if (noteId == null) {
            // No need to populate, it's a new note
            mIsNewNote = true;
            return;
        }
        if (mIsDataLoaded) {
            // No need to populate, already have data.
            return;
        }
        mIsNewNote = false;
        dataLoading.set(true);

        mNotesRepository.getNote(noteId, this);
    }

    @Override
    public void onNoteLoaded(Note note) {
        title.set(note.getTitle());
        content.set(note.getContent());
        dataLoading.set(false);
        mIsDataLoaded = true;
    }

    @Override
    public void onDataNotAvailable() {
        dataLoading.set(false);
    }

    // Called when clicking on fab.
    void saveNote() {
        Note note = new Note(title.get(), content.get(), new Date().getTime());
        if (note.isEmpty()) {
            mSnackbarText.setValue(R.string.empty_note_message);
            return;
        }

        if (isNewNote() || mNoteId == null) {
            createNote(note);
        } else {
            note = new Note(Integer.parseInt(mNoteId), title.get(), content.get(), note.getDate(),
                    note.isStar(), note.isTrash());
            updateNote(note);
        }
    }

    private void createNote(Note note) {
        mNotesRepository.saveNote(note);
        mNoteUpdated.call();
    }

    private void updateNote(Note note) {
        if (isNewNote()) {
            throw new RuntimeException("updateNote() was called but note is new.");
        }
        mNotesRepository.saveNote(note);
        mNoteUpdated.call();
    }

    public boolean isNewNote() {
        return mIsNewNote;
    }

    SingleLiveEvent<Void> getNoteUpdatedEvent() {
        return mNoteUpdated;
    }

    public SnackbarMessage getSnackbarMessage() {
        return mSnackbarText;
    }
}
